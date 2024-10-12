package com.cc.idle.framework.common.enums.biz;

import lombok.Getter;

@Getter
public enum EGameParamType {
    TEST(1001, "测试用户"),

    ;

    private int code;
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
