package com.cc.idle.game.config.conf;

import com.cc.idle.framework.common.conf.base.annotation.GameConfig;
import com.cc.idle.framework.common.util.object.BeanUtils;
import com.cc.idle.game.config.api.conf.Config_GameIdleApi;
import com.cc.idle.game.config.api.conf.dto.Config_GameIdleDTO;
import com.cc.idle.game.config.conf.base._AConfig;
import com.cc.idle.game.config.conf.base._AConfigLoader;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Component
@GameConfig(fileName = "idle.json")
public class Config_gameIdleConfig extends Config_GameIdleDTO implements _AConfig , Config_GameIdleApi {
    @GameConfig(ignoreField = true)
    private static _AConfigLoader<Config_gameIdleConfig> INSTANCE = new _AConfigLoader<>() {
        @Override
        protected Config_gameIdleConfig createNewConfig() {
            return new Config_gameIdleConfig();
        }

        @Override
        protected void afterLoadDone() {

        }
    };

    @Override
    public _AConfigLoader<?> getConfigLoader() {
        return INSTANCE;
    }


    @Override
    public Config_GameIdleDTO get(Long id) {
        return INSTANCE.getById(id);
    }

    @Override
    public List<Config_GameIdleDTO> list() {
        List<Config_gameIdleConfig> list = INSTANCE.list();
        return BeanUtils.toBean(list, Config_GameIdleDTO.class);
    }
}
