package com.cc.idle.game.config.gameconfig;

import com.cc.idle.framework.common.conf.base._IResPathDealer;
import org.springframework.stereotype.Service;

@Service
public class GameResourceService implements _IResPathDealer {
    @Override
    public String getGameResourcePath(String path) {
        return "localhost:10800/" + "res/" + path + ".png";
    }
}
