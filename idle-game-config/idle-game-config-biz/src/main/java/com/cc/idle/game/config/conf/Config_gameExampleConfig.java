package com.cc.idle.game.config.conf;

import com.cc.idle.framework.common.conf.base.annotation.GameConfig;
import com.cc.idle.game.config.api.conf.dto.Config_GameExampleDTO;
import com.cc.idle.game.config.conf.base._AConfig;
import com.cc.idle.game.config.conf.base._AConfigLoader;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Component
@GameConfig(fileName = "gameExampleConfig.json")
public class Config_gameExampleConfig extends Config_GameExampleDTO implements _AConfig {
    @GameConfig(ignoreField = true)
    private static _AConfigLoader<Config_gameExampleConfig> INSTANCE = new _AConfigLoader<>() {
        @Override
        protected Config_gameExampleConfig createNewConfig() {
            return new Config_gameExampleConfig();
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
