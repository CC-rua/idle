package com.cc.idle.game.config.api.conf;


import com.cc.idle.framework.rpc.config.FeignConfiguration;
import com.cc.idle.game.config.api.conf.dto.Config_GameConstDTO;
import com.cc.idle.game.config.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(configuration = FeignConfiguration.class, name = ApiConstants.NAME) //
@Tag(name = "RPC 服务 - 通用参数配置配置")
public interface Config_GameConstApi {
    String PREFIX = ApiConstants.PREFIX + "/const";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "根据参数键查询参数值")
    Config_GameConstDTO get();
}
