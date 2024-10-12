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
