package com.cc.idle.framework.common.enums.biz;

import lombok.Getter;

/**
 * 给客户端的通知
 */
@Getter
public enum EGameNotifyType {
    //弹窗
    TIPS_NOTIFY(1001, "通用信息弹窗"),
    //红点
    RED_SLOT_ACHIEVE(2001, "成就红点"),
    RED_SLOT_DAY_SIGN(2002, "每日签到红点"),
    RED_SLOT_ACCUMULATE_SIGN(2003, "累积签到红点"),
    RED_SLOT_ADVENTURE_ROAD(2004, "冒险之路红点"),
    RED_SLOT_QUEST(2005, "任务红点"),
    ;

    private int code;
    private String desc;

    EGameNotifyType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EGameNotifyType getByCode(int code) {
        for (EGameNotifyType EGameEventType : EGameNotifyType.values()) {
            if (EGameEventType.getCode() == code) {
                return EGameEventType;
            }
        }
        return null;
    }

    public static String getDescByCode(int code) {
        for (EGameNotifyType EGameEventType : EGameNotifyType.values()) {
            if (EGameEventType.getCode() == code) {
                return EGameEventType.getDesc();
            }
        }
        return null;
    }
}
