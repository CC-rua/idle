package com.cc.idle.game.config.api.conf.dto;

import com.cc.idle.framework.common.conf.base.annotation.ColumnsTableFiled;
import com.cc.idle.framework.common.conf.common.ConfigItem;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Config_GameConstDTO {
    @ColumnsTableFiled(sn = 1)
    public String password;//登录密匙
    @ColumnsTableFiled(sn = 2)
    public ArrayList<ConfigItem> renameCostList;
}
