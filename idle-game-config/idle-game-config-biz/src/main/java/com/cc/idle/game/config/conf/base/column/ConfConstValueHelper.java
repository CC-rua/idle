package com.cc.idle.game.config.conf.base.column;

import com.cc.idle.framework.common.conf.base._IConstValueHelper;
import com.cc.idle.framework.common.conf.base.annotation.ColumnsTable;
import com.cc.idle.game.config.api.conf.Config_GameConstApi;
import com.cc.idle.game.config.api.conf.dto.Config_GameConstDTO;
import com.cc.idle.game.config.conf.Config_gameCommonConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@ColumnsTable(configClass = Config_gameCommonConfig.class)
public class ConfConstValueHelper extends Config_GameConstDTO implements _IConstValueHelper, Config_GameConstApi {

    @Override
    public Config_GameConstDTO get() {
        return this;
    }
}

