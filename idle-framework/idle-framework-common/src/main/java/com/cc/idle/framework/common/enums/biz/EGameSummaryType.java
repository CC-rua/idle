package com.cc.idle.framework.common.enums.biz;

import lombok.Getter;

/**
 * 游戏数值统计方式
 */
@Getter
public enum EGameSummaryType {
    ADD(1, "数值累加"),
    GT(2, "取最大值"),
    SET(3, "覆盖"),
    MIN(4, "取最小值")
    ;

    private int code;
    private String desc;

    EGameSummaryType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EGameSummaryType getByCode(int code) {
        for (EGameSummaryType type : EGameSummaryType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public static String getDescByCode(int code) {
        for (EGameSummaryType type : EGameSummaryType.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }
}
