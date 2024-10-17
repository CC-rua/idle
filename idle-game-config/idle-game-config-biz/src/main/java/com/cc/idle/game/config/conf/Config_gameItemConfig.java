package com.cc.idle.game.config.conf;


import com.cc.idle.framework.common.conf.base.annotation.GameConfig;
import com.cc.idle.framework.common.util.object.BeanUtils;
import com.cc.idle.game.config.api.conf.Config_GameItemApi;
import com.cc.idle.game.config.api.conf.dto.Config_GameItemDTO;
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
@GameConfig(fileName = "item.json")
public class Config_gameItemConfig extends Config_GameItemDTO implements _AConfig, Config_GameItemApi {
    @GameConfig(ignoreField = true)
    private static _AConfigLoader<Config_gameItemConfig> INSTANCE = new _AConfigLoader<>() {
        @Override
        protected Config_gameItemConfig createNewConfig() {
            return new Config_gameItemConfig();
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
    public Config_GameItemDTO get(Long id) {
        return INSTANCE.getById(id);
    }

    @Override
    public List<Config_GameItemDTO> list() {
        List<Config_gameItemConfig> list = INSTANCE.list();
        return BeanUtils.toBean(list, Config_GameItemDTO.class);
    }
}
