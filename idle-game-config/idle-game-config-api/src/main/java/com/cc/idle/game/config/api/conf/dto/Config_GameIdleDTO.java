package com.cc.idle.game.config.api.conf.dto;

import com.cc.idle.framework.common.conf.common.ConfigItem;
import lombok.Data;

@Data
public class Config_GameIdleDTO {
    public Long id;
    public String name;
    public String desc;
    public Integer settleTime;
    public ConfigItem item;
}
