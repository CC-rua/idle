package com.cc.idle.game.config.api.conf;

import com.cc.idle.game.config.api.conf.dto.Config_GameCommonDTO;

import java.util.List;

public interface Config_GameCommonApi {
    Config_GameCommonDTO get(Long id);

    List<Config_GameCommonDTO> list();
}
