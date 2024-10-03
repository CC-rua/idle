package com.cc.idle.framework.common.enums.biz;

import lombok.Getter;

@Getter
public enum EGameParamType {
    HUA_WEI(1001, "华为手环用户"),
    LAST_QUEST_REFRESH_TIME(1002, "上次刷新任务时间"),
    LAST_SIGN_REFRESH_TIME(1003, "上次刷新签到时间"),
    TICKET(1004, "门票绑定"),
    BEST_SCORE_DRIVE_TIME(2001, "卡丁车最高分数"),
    BEST_SCORE_STRIKE(2002, "保龄球最高分数"),
    BEST_SCORE_CLIMBING(2003, "攀岩最高分数"),
    BEST_SCORE_SHOOT(2004, "激光打靶最高分数"),
    BEST_SCORE_PING_PONG(2005, "急速乒乓最高分数"),
    BEST_SCORE_BASKETBALL(2006, "互动篮球最高分数"),
    BEST_SCORE_TEAM_BALL(2007, "互动対打球最高分数"),
    BEST_SCORE_BASE_BALL(2008, "棒球最高分数"),
    BEST_SCORE_FOOT_BALL(2009, "电子足球最高分数"),
    BEST_SCORE_ROCK_CLIMB(2010, "攀岩最高分数"),
    BEST_SCORE_BOOMERANG(2011, "飞镖最高分数"),
    BEST_SCORE_ARCHERY(2012, "飞镖最高分数"),
    BEST_SCORE_CS(2013, "CS最高分数"),
    BEST_SCORE_GOLF(2014, "高尔夫最高分数"),
    BEST_SCORE_DANCE(2015, "跳舞机最高分数"),
    BEST_SCORE_SKIING(2016, "滑雪机最高分数"),
    BEST_SCORE_TENNIS(2017, "网球最高分数"),
    HAS_READ_TEACH_ID_LIST(3001, "已读游戏项目教程id"),
    ;

    private long code;
    private String desc;

    EGameParamType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EGameParamType getByCode(int code) {
        for (EGameParamType type : EGameParamType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public static String getDescByCode(int code) {
        for (EGameParamType type : EGameParamType.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }
}
