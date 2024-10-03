package com.cc.idle.framework.common.enums.biz;

import lombok.Getter;

@Getter
public enum EGameRoundType {
    NONE(0, "无刷新"),
    DAY_OF_MONTH(1, "每月第x天0点刷新 DAY_OF_MONTH:<每月第x天>"),
    HOUR_OF_DAY(2, "每天第x小时刷新 HOUR_OF_DAY:<第x个小时>"),
    DAY_OF_WEEK(3, "每周第x天刷新，规定星期一是一周的第一天 WEEK:<每周第x天>"),
    DAY(4, "每x天刷新 DAY:<每x天>"),
    HOUR(5, "每x小时刷新 HOUR:<每x小时>"),
    SECOND(6, "每x秒刷新 SECOND:<每x秒>"),
    WEEK(7, "每x周刷新 WEEK:<每x周>"),
    MONTH(8, "每x月刷新 WEEK:<每x月>")
    //TODO: 更多刷新方式的实现
    ;

    private int code;
    private String desc;

    EGameRoundType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EGameRoundType getByCode(int code) {
        for (EGameRoundType EGameEventType : EGameRoundType.values()) {
            if (EGameEventType.getCode() == code) {
                return EGameEventType;
            }
        }
        return null;
    }

    public static String getDescByCode(int code) {
        for (EGameRoundType EGameEventType : EGameRoundType.values()) {
            if (EGameEventType.getCode() == code) {
                return EGameEventType.getDesc();
            }
        }
        return null;
    }
}
