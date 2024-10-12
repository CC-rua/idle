package com.cc.idle.game.user.game.rank.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: cc
 * @date: 2024/1/12
 */
@Getter
@Setter
public class RankObjBean {
    //id
    private long id;
    //分数
    private long score;
    //最后更新时间
    private long lastUpdateTimeMs;
    //被点赞次数
    private int praiseNum;
}
