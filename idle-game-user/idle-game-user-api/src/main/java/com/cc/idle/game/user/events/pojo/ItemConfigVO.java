package com.cc.idle.game.user.events.pojo;

import com.cc.idle.framework.common.conf.common.ConfigRes;
import com.cc.idle.framework.common.enums.biz.EGameItemType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemConfigVO {
    @Schema(description = "唯一标识")
    private Long id; // id

    @Schema(description = "计数器")
    private Long count;

    @Schema(description = "名称")
    private String name; // 名称

    @Schema(description = "英文名称")
    private String name_english; // 名称

    @Schema(description = "描述")
    private String desc; // 描述

    @Schema(description = "类型")
    private EGameItemType type; // 类型

    @Schema(description = "时间限制（单位天）")
    private Integer timeLimit; // 时间限制(单位天)

    @Schema(description = "图标资源")
    private ConfigRes icon; // 图标资源
}
