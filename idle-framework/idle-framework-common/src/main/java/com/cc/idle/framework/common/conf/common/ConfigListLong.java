package com.cc.idle.framework.common.conf.common;


import com.cc.idle.framework.common.conf.base._IStringConvertible;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ConfigListLong implements _IStringConvertible {
    private List<Long> valueList;

    public ConfigListLong() {
        valueList = new ArrayList<>();
    }

    @Override
    public void convert(String value) {
        valueList = new ArrayList<>();
        String[] split = value.split(":");
        for (String v : split) {
            Long id = Long.parseLong(v);
            valueList.add(id);
        }
    }

    @Override
    public String toString() {
        return valueList.stream().map(Object::toString).collect(Collectors.joining(":"));
    }
}
