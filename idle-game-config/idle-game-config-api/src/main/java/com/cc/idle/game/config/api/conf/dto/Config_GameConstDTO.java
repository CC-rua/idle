package com.cc.idle.game.config.api.conf.dto;

import com.cc.idle.framework.common.conf.base.annotation.ColumnsTableFiled;
import com.cc.idle.framework.common.conf.common.ConfigItem;
import com.cc.idle.framework.common.conf.common.ConfigRound;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Config_GameConstDTO {
    @ColumnsTableFiled(sn = 1)
    public ConfigItem signCompensateCost;//补签消耗
    @ColumnsTableFiled(sn = 2)
    public ArrayList<ConfigItem> renameCostList;//改名消耗
    @ColumnsTableFiled(sn = 3)
    public long adventureRoadStarItemId;//冒险之路冒险之星的道具id
    @ColumnsTableFiled(sn = 4)
    public long adventureRoadStarHuaweiBuff;//智能手环用户对冒险之星获取量的增益效果（万分比）
    @ColumnsTableFiled(sn = 5)
    public long defaultIconBgkId;//默认头像框id
    @ColumnsTableFiled(sn = 6)
    public long defaultIconId;//默认头像id
    @ColumnsTableFiled(sn = 7)
    public ConfigRound questRound;//任务进度每天5点刷新
    @ColumnsTableFiled(sn = 8)
    public ConfigRound signRound;//签到进度每月第一天刷新
}
