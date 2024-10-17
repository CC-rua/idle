package com.cc.idle.game.config.api.conf;


import com.cc.idle.game.config.api.conf.dto.Config_GameItemDTO;
import com.cc.idle.game.config.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = ApiConstants.NAME) //
@Tag(name = "RPC 服务 - 道具配置")
public interface Config_GameItemApi {
    String PREFIX = ApiConstants.PREFIX + "/item";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "根据参数键查询参数值")
    Config_GameItemDTO get(Long id);

    @GetMapping(PREFIX + "/list")
    @Operation(summary = "获取列表")
    List<Config_GameItemDTO> list();
}
