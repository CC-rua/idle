package com.cc.idle.framework.common.id;

import cn.hutool.core.date.DateUtil;
import com.cc.idle.framework.common.conf.base.GameConst;
import com.cc.idle.framework.common.enums.biz.EGameIdType;
import com.cc.idle.framework.common.util.date.GlobalTimeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongBinaryOperator;

/**
 * 代理分配唯一ID
 * <p>
 * 规则： 5位服务器Id + 9位(服务器启动时间 -【2016-01-01 00:00:00】) + 5位自增长Id
 * 注意：
 * 1.从2016-01-01 00:00:00起到2046-01-01 00:00:00之间 分配id不好出问题。
 * 2.5位服务器Id必须小于92230
 *
 * @author gjp
 */

@Slf4j
public class IdManager {

    private long startServerTime = -1;

    private Date beginTime;

    private AtomicLong[] preIds = new AtomicLong[EGameIdType.ID_TYPE_MAX.ordinal()];

    /**
     *
     */
    private AtomicLong[] ids = new AtomicLong[EGameIdType.ID_TYPE_MAX.ordinal()];

    private static IdManager instance = new IdManager();

    private IdManager() {
        beginTime = DateUtil.parseDateTime("2016-01-01 00:00:00");
        for (int i = 0; i < EGameIdType.ID_TYPE_MAX.ordinal(); i++) {
            ids[i] = new AtomicLong(0);
        }
        for (int i = 0; i < EGameIdType.ID_TYPE_MAX.ordinal(); i++) {
            preIds[i] = new AtomicLong(0);
        }
        init(GlobalTimeUtil.getNow());
    }

    public void setBeginTime(Date time) {
        beginTime = time;
    }

    public static IdManager getInstance() {
        return instance;
    }

    private long genId(EGameIdType id_type) {
        long maxId = ids[id_type.ordinal()].accumulateAndGet(1L, new LongBinaryOperator() {
            @Override
            public long applyAsLong(long index, long increment) {
                ++index;
                if (index >= 100000) { // maxId 大于5位数重置前置Id
                    resetPreId(id_type);
                    return 0;
                } else {
                    return index;
                }
            }
        });
        return preIds[id_type.ordinal()].get() + maxId;
    }

    /**
     * 此方法至关重要
     * <p>
     * 当ID分配系统不可用时，或者出现错误时，会阻塞住调用者 当ID类型不正确时会报错
     *
     * @param id_type 分配ID的类型
     * @return
     */
    public long applicateId(EGameIdType id_type) {
        return genId(id_type);
    }

    public void init(long startTime) {
        init(false, startTime);
    }

    /**
     * 启动时初始化
     */
    private void init(boolean isTest, long startTime) {
        if (isTest) {
            startServerTime = GlobalTimeUtil.getNow();
        } else {
            startServerTime = startTime;
        }

        // 服务器id 限制为5为数字且小于 92230
        if (GameConst.serverId > 92230) {
            System.err.println("初始化IdManager错误,服务器Id不能超过5位数字且小于 92230");
            log.error("服务器Id不能超过5位数字且小于 92230 初始化IdManager错误");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            System.exit(0);
        }

        // 间隔时间
        long intervalTime = (startServerTime - beginTime.getTime()) / GameConst.SECOND;
        // 间隔时间不能超过9位数
        if (intervalTime >= 1000000000L) {
            log.error("间隔时间不能超过9位数,重新设置开始时间 初始化IdManager错误");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            System.exit(0);
        }
        for (byte i = 0; i < EGameIdType.ID_TYPE_MAX.ordinal(); i++) {
            preIds[i].set(GameConst.serverId * 100000000000000L + intervalTime * 100000L);
        }
    }

    void resetPreId(EGameIdType id_type) {
        // 间隔时间
        long intervalTime = (GlobalTimeUtil.getNow() - beginTime.getTime()) / GameConst.SECOND;
        // 间隔时间不能超过9位数
        if (intervalTime >= 1000000000L) {
            log.error(" resetPreId 间隔时间不能超过9位数,重新设置开始时间 初始化IdManager错误");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            System.exit(0);
        }
        preIds[id_type.ordinal()].set(GameConst.serverId * 100000000000000L + intervalTime * 100000L);

        log.info("IdManager重置前置的Id!!!");
    }

}
