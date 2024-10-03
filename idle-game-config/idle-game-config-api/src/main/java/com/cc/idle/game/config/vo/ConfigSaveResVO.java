package com.cc.idle.game.config.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigSaveResVO {
    @Schema(description = "原始文件路径")
    private String originalFilePath;
    @Schema(description = "低质量文件路径")
    private String lowQualityFilePath;
}
