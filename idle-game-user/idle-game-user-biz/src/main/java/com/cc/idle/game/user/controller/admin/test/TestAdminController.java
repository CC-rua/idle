package com.cc.idle.game.user.controller.admin.test;

import com.cc.idle.framework.common.pojo.CommonResult;
import com.cc.idle.game.config.api.conf.dto.Config_GameConstDTO;
import com.cc.idle.game.user.service.TestFeignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cc.idle.framework.common.pojo.CommonResult.success;

@Tag(name = "管理 - 用户服务器 - 测试")
@RestController
@RequestMapping("/game-user/test")
@Validated
@Slf4j
public class TestAdminController {
    @Resource
    private TestFeignService testFeignService;

    @GetMapping("/hello")
    @Operation(summary = "说你好")
    public CommonResult<String> login() {
        return success("hello world");
    }

    @GetMapping("/feign")
    @Operation(summary = "调用 feign 服务")
    public CommonResult<Config_GameConstDTO> feign() {
        return success(testFeignService.getGameConst());
    }
}
