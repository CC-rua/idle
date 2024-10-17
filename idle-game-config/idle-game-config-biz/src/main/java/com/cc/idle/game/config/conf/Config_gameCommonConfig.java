package com.cc.idle.game.config.conf;


import com.cc.idle.framework.common.conf.base.annotation.GameConfig;
import com.cc.idle.framework.common.util.object.BeanUtils;
import com.cc.idle.game.config.conf.base._AConfig;
import com.cc.idle.game.config.conf.base._AConfigLoader;
import com.cc.idle.game.config.api.conf.dto.Config_GameCommonDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@RequiredArgsConstructor
@Component
@GameConfig(fileName = "common.json")
public class Config_gameCommonConfig extends Config_GameCommonDTO implements _AConfig {
    @GameConfig(ignoreField = true)
    private static _AConfigLoader<Config_gameCommonConfig> INSTANCE = new _AConfigLoader<>() {
        @Override
        protected Config_gameCommonConfig createNewConfig() {
            return new Config_gameCommonConfig();
        }

        @Override
        protected void afterLoadDone() {

        }
    };

    @Override
    public _AConfigLoader<?> getConfigLoader() {
        return INSTANCE;
    }



}
