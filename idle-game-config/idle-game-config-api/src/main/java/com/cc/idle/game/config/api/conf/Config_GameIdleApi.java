package com.cc.idle.game.config.api.conf;


import com.cc.idle.game.config.api.conf.dto.Config_GameIdleDTO;
import com.cc.idle.game.config.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = ApiConstants.NAME) //
@Tag(name = "RPC 服务 - 挂机配置")
public interface Config_GameIdleApi {
    String PREFIX = ApiConstants.PREFIX + "/idle";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "根据参数键查询参数值")
    Config_GameIdleDTO get(Long id);

    @GetMapping(PREFIX + "/list")
    @Operation(summary = "获取列表")
    List<Config_GameIdleDTO> list();
}
