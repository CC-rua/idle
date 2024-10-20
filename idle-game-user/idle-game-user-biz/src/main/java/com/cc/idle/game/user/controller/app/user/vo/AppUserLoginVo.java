package com.cc.idle.game.user.controller.app.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户登录 Response VO")
public class AppUserLoginVo {
    private Long id;
    private String name;
}
