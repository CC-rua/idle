package com.cc.idle.game.user.game.rank.base;

import lombok.Getter;

/**
 * @author: cc
 * @date: 2024/1/18
 */
@Getter
public enum ERankType {
    NO_USE(0),
    PLAYER_SCORE_RANK(1),//用户积分排行
    ;

    private long value;

    public static ERankType getFromIntValue(long value) {
        for (ERankType type : ERankType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return NO_USE;
    }

    ERankType(int value) {
        this.value = value;
    }
}
