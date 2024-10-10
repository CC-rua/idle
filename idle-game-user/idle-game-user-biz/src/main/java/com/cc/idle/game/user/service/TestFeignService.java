package com.cc.idle.game.user.service;

import com.cc.idle.game.config.api.conf.Config_GameConstApi;
import com.cc.idle.game.config.api.conf.dto.Config_GameConstDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TestFeignService {
    @Resource
    private Config_GameConstApi configGameConstApi;

    public Config_GameConstDTO getGameConst() {
        return configGameConstApi.get();
    }
}
