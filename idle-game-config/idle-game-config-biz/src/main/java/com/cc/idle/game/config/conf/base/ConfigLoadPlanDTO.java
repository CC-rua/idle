package com.cc.idle.game.config.conf.base;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ConfigLoadPlanDTO {
    private String configName;
    private File jsonFile;
    private _AConfig config;

    /**
     * 验证计划的完整性
     */
    public boolean validate() {
        if (!ObjectUtil.isAllNotEmpty(configName, jsonFile, config)) {
            log.error("配置计划不完整,configName: {},json: {},config: {}", configName, jsonFile, config);
            return false;
        }
        return true;
    }
}
