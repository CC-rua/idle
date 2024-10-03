package com.cc.idle.framework.common.conf.base;

public interface _IStringConvertible {
    /**
     * 转换成对象
     *
     * @param value
     */
    void convert(String value);

    /**
     * 对象转化成string
     * 必须要重写 tostring
     *
     * @return
     */
    String toString();
}
