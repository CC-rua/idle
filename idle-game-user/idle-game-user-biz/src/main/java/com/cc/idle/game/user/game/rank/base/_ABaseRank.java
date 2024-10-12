package com.cc.idle.game.user.game.rank.base;

import com.cc.idle.framework.common.enums.biz.EGameSummaryType;
import com.cc.idle.framework.common.util.date.GlobalTimeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 排行榜
 *
 * @author: cc
 * @date: 2024/1/12
 */
@Slf4j
public abstract class _ABaseRank<OBJ extends _IRankObj> {
    /**
     * 增加排行查询速率
     */
    protected final ConcurrentHashMap<_IRankObjIndex, OBJ> rankMap;
    /**
     * 排行榜,数组的下标作为排名
     */
    protected final LinkedList<OBJ> rankList;
    /**
     * 保护两个数据集数据同步的锁
     */
    protected final ReentrantLock lock;


    public _ABaseRank() {
        rankMap = new ConcurrentHashMap<>();
        rankList = new LinkedList<>();
        lock = new ReentrantLock();
    }


    /**
     * 查询
     *
     * @param index
     * @return
     */
    public OBJ lookup(_IRankObjIndex index) {
        lock.lock();
        try {
            return rankMap.get(index);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 新增
     *
     * @param rankObj
     */
    public void add(OBJ rankObj) {
        lock.lock();
        try {
            //检查是否已经存在
            OBJ lookup = lookup(rankObj.getIndex());
            if (lookup != null) {
                log.info("add exist rank obj {}", rankObj);
                return;
            }
            //同步更新map和list
            rankMap.put(rankObj.getIndex(), rankObj);
            rankList.add(rankObj);

        } finally {
            lock.unlock();
        }
    }


    /**
     * 从一个有序列表中查找rankObj的新位置
     *
     * @param rankObj 待重排对象
     * @return
     */
    private int findSlot(OBJ rankObj) {
        lock.lock();
        try {
            int low = 0;
            int high = rankList.size() - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                OBJ midElement = rankList.get(mid);

                int comparisonResult = compare(rankObj, midElement);

                if (comparisonResult > 0) {
                    low = mid + 1; // 目标对象在右侧
                } else if (comparisonResult < 0) {
                    high = mid - 1; // 目标对象在左侧
                } else {
                    low = mid;//等于当前位置
                    break;
                }
            }

            return low; // 返回对象应该插入的位置
        } finally {
            lock.unlock();
        }
    }


    /**
     * 改变分数
     *
     * @param index
     * @param score
     * @param changeType
     */
    public void changeScoreAndSort(_IRankObjIndex index, long score, EGameSummaryType changeType) {
        OBJ rankObj = lookup(index);
        boolean isExist = rankObj != null;
        //保证对象在 map中存在,在list中不存在
        if (!isExist) {
            rankObj = createRankObj(index);
            rankMap.put(index, rankObj);
        } else {
            //先从列表中移除
            rankList.remove(rankObj);
        }
        //改变分数
        changeScore(rankObj, score, changeType);

        //保证对象插入到正确的位置
        int findSlot = findSlot(rankObj);
        rankList.add(findSlot, rankObj);

    }

    /**
     * 变更排行榜分数
     */
    public void changeScore(OBJ rankObj, long score, EGameSummaryType changeType) {
        lock.lock();
        try {
            long currentScore = rankObj.getScore();
            switch (changeType) {
                case ADD: {
                    currentScore = currentScore + score;
                }
                break;
                case SET: {
                    currentScore = score;
                }
                break;
                case GT: {
                    currentScore = Math.max(currentScore, score);
                }
                break;
            }
            rankObj.setScore(currentScore);
            rankObj.setLastRefreshTime(GlobalTimeUtil.getNow());

            rankObj.dbSave();
        } finally {
            lock.unlock();
        }
    }

    public void remove(_IRankObjIndex index) {
        lock.lock();
        try {
            //检查是否已经存在
            OBJ lookup = lookup(index);
            if (lookup == null) {
                log.info("remove not exist rank obj. index:{}", index);
                return;
            }
            //同步更新map和list
            rankMap.remove(index);
            rankList.remove(lookup);

            lookup.dbDelete();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 查询排名
     *
     * @param rankObj
     * @return
     */
    public int lookupOrder(OBJ rankObj) {
        return rankList.indexOf(rankObj) + 1;
    }


    /**
     * 通过排名查找排行数据
     *
     * @param order
     * @return
     */
    public OBJ lookupByOrder(int order) {
        return rankList.get(order - 1);
    }

    public void clear() {
        lock.lock();
        try {
            rankMap.clear();
            rankList.clear();
        } finally {
            lock.unlock();
        }
    }

    public abstract long getRankId();

    public abstract int getSerial();

    public abstract long getLastRefreshTimeMs();

    protected abstract int compare(OBJ rankObj, OBJ iRankObj);

    protected abstract void save();

    public abstract OBJ createRankObj(_IRankObjIndex index);

    public abstract void refresh();

    public abstract int getType();

    /**
     * 排行榜刷新到下一个周期
     *
     * @param globalTimeMs
     */
    public abstract void done(long globalTimeMs);

}
