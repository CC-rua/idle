package com.cc.idle.framework.common.enums.biz;

import lombok.Getter;

/**
 * redis的字符串key
 */
@Getter
public enum EGameRedisKey {
    NONE(""),
    ;
    private String key;

    EGameRedisKey(String key) {
        this.key = key;
    }
}
