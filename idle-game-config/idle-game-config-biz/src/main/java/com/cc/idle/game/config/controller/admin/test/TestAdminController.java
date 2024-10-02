package com.cc.idle.game.config.controller.admin.test;

import com.cc.idle.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cc.idle.framework.common.pojo.CommonResult.success;

@Tag(name = "游戏配置 - 测试")
@RestController
@RequestMapping("/game-config/test")
@Validated
@Slf4j
public class TestAdminController {
    @GetMapping("/hello")
    @PermitAll
    @Operation(summary = "说你好")
    public CommonResult<String> login() {
        return success("hello world");
    }
}
