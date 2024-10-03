package com.cc.idle.framework.common.enums.biz;

import lombok.Getter;

@Getter
public enum EGameEventType {
    // 游戏成绩上报
    GAME_SCORE_POST_SHOOT(1001, "游戏成绩上报激光打靶"),
    GAME_SCORE_POST_DRIVE_TIME(1002, "游戏成绩上报卡丁车"),
    GAME_SCORE_POST_BASKETBALL(1003, "游戏成绩上报篮球"),
    GAME_SCORE_POST_STRIKE(1004, "游戏成绩上报保龄球"),
    GAME_SCORE_POST_ROCK_CLIMBING(1005, "游戏成绩上报攀岩"),
    GAME_SCORE_POST_PING_PONG(1006, "游戏成绩上报乒乓球"),
    GAME_SCORE_POST_TEAM_BALL(1007, "游戏成绩上报互动对打球"),
    GAME_SCORE_BASEBALL(1008, "游戏成绩上报棒球"),
    GAME_SCORE_FOOTBALL(1009, "游戏成绩上报电子足球"),
    GAME_SCORE_ROCK_CLIMB(1009, "游戏成绩上报攀岩"),
    GAME_SCORE_BOOMERANG(1010, "游戏成绩上报飞镖"),
    GAME_SCORE_ARCHERY(1011, "游戏成绩上报射箭"),
    GAME_SCORE_CS(1012, "游戏成绩上报CS"),
    GAME_SCORE_GOLF(1013, "游戏成绩上报高尔夫"),
    GAME_SCORE_DANCE(1014, "游戏成绩上报跳舞机"),
    GAME_SCORE_SKIING(1015, "游戏成绩上报滑雪机"),
    GAME_SCORE_TENNIS(1016, "游戏成绩上报网球"),
    //玩家事件
    PLAYER_ITEM_GAIN(2101, "玩家获得道具"),
    PLAYER_ITEM_CONSUME(2102, "玩家消耗道具"),
    PLAYER_FINISH_TEACH(2103, "玩家完成教程"),
    PLAYER_FIRST_LOGIN(2104, "玩家首次登陆"),
    //玩家事件-排行
    PLAYER_RANK_SETTLE(2201, "排行榜结算"),
    PLAYER_RANK_REWARD(2202, "玩家领取排行榜结算奖励"),
    //玩家事件-签到
    PLAYER_SIGN(2301, "玩家签到"),
    //玩家事件-任务
    PLAYER_QUEST(2401, "玩家领取任务奖励"),
    //玩家事件-商店
    PLAYER_SHOP_BUY(2501, "玩家商店兑换"),
    //成绩上报
    PLAYER_RESULT(2601, "玩家成绩上报"),
    ;

    private int code;
    private String desc;

    EGameEventType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EGameEventType getByCode(int code) {
        for (EGameEventType EGameEventType : EGameEventType.values()) {
            if (EGameEventType.getCode() == code) {
                return EGameEventType;
            }
        }
        return null;
    }

    public static String getDescByCode(int code) {
        for (EGameEventType EGameEventType : EGameEventType.values()) {
            if (EGameEventType.getCode() == code) {
                return EGameEventType.getDesc();
            }
        }
        return null;
    }
}
