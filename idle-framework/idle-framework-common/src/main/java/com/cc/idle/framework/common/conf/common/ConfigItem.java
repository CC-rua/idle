package com.cc.idle.framework.common.conf.common;


import com.cc.idle.framework.common.conf.base._IStringConvertible;
import lombok.Data;

@Data
public class ConfigItem implements _IStringConvertible {
    private long id;
    private long count;
    public ConfigItem() {
    }

    public ConfigItem(long itemId, Long itemCount) {
        this.id = itemId;
        this.count = itemCount;
    }

    @Override
    public void convert(String value) {
        String[] split = value.split(":");
        if (split.length > 0) {
            id = Long.parseLong(split[0]);
        }
        if (split.length > 1) {
            count = Long.parseLong(split[1]);
        }
    }

    @Override
    public String toString() {
        return String.format("%d:%d", id, count);
    }
}
