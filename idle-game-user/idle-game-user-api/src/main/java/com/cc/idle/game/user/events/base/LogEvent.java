package com.cc.idle.game.user.events.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class LogEvent {
    @Schema(description = "player_game_role 表 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23953")
    @NotNull(message = "player_game_role 表 id不能为空")
    private long roleId;

    @Schema(description = "角色昵称", example = "赵六")
    private String roleName;

    @Schema(description = "手机号")
    private String phoneNum;

}
