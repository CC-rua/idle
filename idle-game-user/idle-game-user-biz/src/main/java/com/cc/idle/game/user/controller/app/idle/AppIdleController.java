package com.cc.idle.game.user.controller.app.idle;

import com.cc.idle.framework.common.pojo.CommonResult;
import com.cc.idle.game.user.game.PlayerDomain;
import com.cc.idle.game.user.game.PlayerDomainMgr;
import com.cc.idle.game.user.game.comp.idle.IdleComponent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.cc.idle.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 - 用户服务器 - 挂机")
@RestController
@RequestMapping("/game-user/idle")
@Validated
@Slf4j
public class AppIdleController {
    @Resource
    private PlayerDomainMgr playerDomainMgr;

    @GetMapping("/start")
    @Operation(summary = "开始产出")
    public CommonResult<Boolean> start(@RequestParam
                                       @Parameter(description = "用户id")
                                       Long roleId,
                                       @RequestParam
                                       @Parameter(description = "配置id")
                                       Long configId) {
        PlayerDomain playerDomain = playerDomainMgr.getOrCreatePlayerDomain(roleId);
        IdleComponent idleComponent = playerDomain.getComponentByType(IdleComponent.class);
        idleComponent.start(configId);
        return success(Boolean.TRUE);
    }

    @GetMapping("/end")
    @Operation(summary = "结束产出")
    public CommonResult<Boolean> end(@RequestParam
                                     @Parameter(description = "用户id")
                                     Long roleId,
                                     @RequestParam
                                     @Parameter(description = "配置id")
                                     Long configId) {
        PlayerDomain playerDomain = playerDomainMgr.getOrCreatePlayerDomain(roleId);
        IdleComponent idleComponent = playerDomain.getComponentByType(IdleComponent.class);
        idleComponent.end(configId);
        return success(Boolean.TRUE);
    }
}
