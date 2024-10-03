package com.cc.idle.game.config.api.conf.dto;

import com.cc.idle.framework.common.conf.base.annotation.GameConfig;
import com.cc.idle.framework.common.conf.common.ConfigExpression;
import com.cc.idle.framework.common.conf.common.ConfigItem;
import com.cc.idle.framework.common.conf.common.ConfigRes;
import com.cc.idle.framework.common.conf.common.ConfigRound;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Config_GameExampleDTO {
    public Long id;
    public ConfigItem item;
    public ArrayList<ConfigItem> itemList;
    public String name;
    public Integer intNum;
    public ConfigRes icon;
    public ConfigExpression expression;
    public Long expressionParam;
    public String expressionDesc;
    public ConfigRound round;
    public String roundDesc;
    @GameConfig(ignoreField = true)
    public String iconIgnore;
}
