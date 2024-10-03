package com.cc.idle.framework.common.conf.common;

import com.cc.idle.framework.common.conf.base._IResPathDealer;
import com.cc.idle.framework.common.conf.base._IStringConvertible;
import com.cc.idle.framework.common.util.spring.SpringUtils;
import lombok.Data;

@Data
public class ConfigRes implements _IStringConvertible {
    private String conf;
    public String url;

    @Override
    public void convert(String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        conf = value;
        _IResPathDealer resDealer = SpringUtils.getApplicationContext().getBean(_IResPathDealer.class);
        url = resDealer.getGameResourcePath(conf);
    }

    @Override
    public String toString() {
        return conf;
    }
}
