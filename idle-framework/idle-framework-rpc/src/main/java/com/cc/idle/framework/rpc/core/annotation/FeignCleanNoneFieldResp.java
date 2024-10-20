package com.cc.idle.framework.rpc.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 在使用feign时，如果返回的json中，有字段为 null 或 “” 则不返回该字段
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FeignCleanNoneFieldResp {
}
