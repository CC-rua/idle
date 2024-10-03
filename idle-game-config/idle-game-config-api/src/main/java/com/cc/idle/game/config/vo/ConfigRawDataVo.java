package com.cc.idle.game.config.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConfigRawDataVo {
    private List<ConfigMateDataVo> metaDataList;

    public ConfigRawDataVo() {
        metaDataList = new ArrayList<>();
    }
}
