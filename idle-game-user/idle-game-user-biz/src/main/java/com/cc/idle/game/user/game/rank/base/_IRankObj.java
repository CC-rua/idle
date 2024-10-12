package com.cc.idle.game.user.game.rank.base;

/**
 * 排行榜单条数据
 *
 * @author: cc
 * @date: 2024/1/12
 */
public interface _IRankObj extends Comparable<Long> {
    //获取排行数据索引
    _IRankObjIndex getIndex();

    //获取分数
    long getScore();

    //设置分数
    void setScore(long score);

    //构造排行数据bean
    RankObjBean makeRankObjBean();

    //获取排名
    int lookupOrder();

    //获取点赞次数
    int getPraise();

    //记录点赞次数
    void setPraise(int praiseNum);

    //获取最后刷新时间
    long getLastRefreshTime();

    //设置最后刷新时间
    void setLastRefreshTime(long time);

    void dbSave();

    void dbDelete();

}
