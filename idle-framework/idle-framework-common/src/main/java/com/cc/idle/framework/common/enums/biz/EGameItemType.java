package com.cc.idle.framework.common.enums.biz;

import lombok.Getter;

@Getter
public enum EGameItemType {
    ITEM(1, "虚拟材料无任何特殊功能"),
    ICON(2, "头像，可穿戴在头像栏"),
    ICON_BGK(3, "头像框，可穿戴在头像框栏");

    private int code;
    private String desc;

    EGameItemType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EGameItemType getByCode(int code) {
        for (EGameItemType type : EGameItemType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public static String getDescByCode(int code) {
        for (EGameItemType type : EGameItemType.values()) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return null;
    }
}
