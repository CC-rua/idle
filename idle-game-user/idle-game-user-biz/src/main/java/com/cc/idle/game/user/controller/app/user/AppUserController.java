package com.cc.idle.game.user.controller.app.user;

import com.cc.idle.framework.common.exception.util.ServiceExceptionUtil;
import com.cc.idle.framework.common.pojo.CommonResult;
import com.cc.idle.framework.common.util.object.BeanUtils;
import com.cc.idle.game.user.controller.app.user.vo.AppUserLoginVo;
import com.cc.idle.game.user.enums.ErrorCodeConstants;
import com.cc.idle.game.user.game.PlayerDomainMgr;
import com.cc.idle.game.user.mybaits.generator.domain.PlayerGameRoleDo;
import com.cc.idle.game.user.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.cc.idle.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 - 用户服务器 - 挂机")
@RestController
@RequestMapping("/game-user/user")
@Validated
@Slf4j
public class AppUserController {
    @Resource
    private RoleService roleService;

    @PostMapping("/register")
    @Operation(summary = "注册")
    public CommonResult<Boolean> register(@RequestParam
                                          @Parameter(description = "用户名")
                                          String roleName) {
        boolean res = roleService.createRole(roleName);
        return success(res);
    }

    @PostMapping("/login")
    @Operation(summary = "登录")
    public CommonResult<AppUserLoginVo> login(@RequestParam
                                       @Parameter(description = "用户名")
                                       String roleName) {
        PlayerGameRoleDo role = roleService.getRole(roleName);
        if (role == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.GAME_USER_NOT_EXISTS);
        }
        return success(BeanUtils.toBean(role, AppUserLoginVo.class));
    }


}
