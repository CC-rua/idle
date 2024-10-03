package com.cc.idle.framework.common.enums.biz;

import lombok.Getter;

@Getter
public enum EGameExpressionType {
    //玩家事件
    PLAYER_GAIN_ITEM_COUNT(1001, "玩家获得某种道具的数量 PLAYER_GAIN_ITEM_COUNT:<道具配置表id>"),
    PLAYER_HAS_ITEM_COUNT(1002, "玩家拥有某种道具的数量 PLAYER_HAS_ITEM_COUNT:<道具配置表id>"),
    PLAYER_CONSUME_ITEM_COUNT(1003, "玩家消耗某种道具的数量 PLAYER_CONSUME_ITEM_COUNT:<道具配置表id>"),
    PLAYER_QUEST_FINISH_COUNT(1004, "玩家完成任务数量 PLAYER_QUEST_FINISH_COUNT"),
    PLAYER_FINISH_TEACH(1005, "玩家完成教程"),
    //游戏结果
    PLAYER_SHOOT_RESULT(2001, "玩家打靶游戏结果 PLAYER_SHOOT_RESULT:<打靶游戏结果类型 TOTAL_ON_ONCE-一局游戏中的总环数 MIN_ON_ONCE-一局游戏中的最小环数,TEN_ON_ONCE-一局游戏中的十环数,TEN-累积十环数>"),
    PLAYER_CAR_RESULT(2002, "玩家卡丁车赛游戏结果 PLAYER_CAR_RESULT:<游戏结果类型 TIME_ON_ONCE-一局游戏中的最佳用时>"),
    PLAYER_BOWLING_RESULT(2003, "玩家保龄球游戏结果 PLAYER_BOWLING_RESULT:<游戏结果类型 SCORE_ON_ONCE-一局游戏中的分数, STRIKE-累积STRIKE次数>"),
    PLAYER_ROCK_CLIMBING_RESULT(2004, "玩家攀岩游戏结果 PLAYER_CLIMBING_RESULT:<游戏结果类型 TIME_ON_ONCE-一局游戏中的最佳用时>"),
    PLAYER_TABLE_TENNIS_RESULT(2005, "玩家乒乓挑战游戏结果 PLAYER_TABLE_TENNIS_RESULT:<游戏模式 ENTERTAINMENT_MODE-【娱乐模式】TRAINING_MODE -【训练模式】>:<游戏结果类型 SCORE_ON_ONCE-一局游戏中的分数,COMBOS_ON_ONCE-最大连击数>"),
    PLAYER_BASKETBALL_RESULT(2006, "玩家篮球挑战游戏结果 PLAYER_BASKETBALL_RESULT:<游戏结果类型 SCORE_ON_ONCE-一局游戏中的分数>"),
    PLAYER_BATTLE_BALL_RESULT(2007, "玩家互动对打球游戏结果 PLAYER_BATTLE_BALL_RESULT:<游戏结果类型 WIN-累积胜利次数>"),
    PLAYER_BASE_BALL_RESULT(2008, "玩家棒球打球结果 PLAYER_BASE_BALL_RESULT:<游戏结果类型 SCORE_ON_ONCE-一局内的分数>"),
    PLAYER_FOOT_BALL_RESULT(2009, "玩家电子足球结果 PLAYER_FOOT_BALL_RESULT:<游戏结果类型 SCORE_ON_ONCE-一局内的分数>"),
    PLAYER_BOOMERANG_RESULT(2010, "玩家飞镖结果 PLAYER_BOOMERANG_RESULT:<游戏结果类型 SCORE_ON_ONCE-一局内的分数>"),
    PLAYER_ARCHERY_RESULT(2011, "玩家射箭结果 PLAYER_ARCHERY_RESULT:<游戏结果类型 SCORE_ON_ONCE-一局内的分数>"),
    PLAYER_CS_RESULT(2012, "玩家CS结果 PLAYER_CS_RESULT:<游戏结果类型 SCORE_ON_ONCE-一局内的分数>"),
    PLAYER_GOLF_RESULT(2013, "玩家高尔夫结果 PLAYER_GOLF_RESULT:<游戏结果类型 SCORE_ON_ONCE-一局内挥杆数 HIT_HOLE_ON_ONCE-一局内进洞数>"),
    PLAYER_DANCE_RESULT(2014, "玩家跳舞机结果 PLAYER_DANCE_RESULT:<游戏结果类型 SCORE_ON_ONCE-一局内挥杆数 HIT_HOLE_ON_ONCE-一局内进洞数>"),
    PLAYER_SKIING_RESULT(2015, "玩家滑雪机结果 PLAYER_SKIING_RESULT:<游戏结果类型 SCORE_ON_ONCE-一局内挥杆数 HIT_HOLE_ON_ONCE-一局内进洞数>"),
    PLAYER_TENNIS_RESULT(2016, "玩家网球结果 PLAYER_TENNIS_RESULT:<游戏结果类型 SCORE_ON_ONCE-一局游戏中的总分数>"),
    ;

    private int code;
    private String desc;

    EGameExpressionType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EGameExpressionType getByCode(int code) {
        for (EGameExpressionType type : EGameExpressionType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public static String getDescByCode(int code) {
        for (EGameExpressionType EGameQuestType : EGameExpressionType.values()) {
            if (EGameQuestType.getCode() == code) {
                return EGameQuestType.getDesc();
            }
        }
        return null;
    }
}
