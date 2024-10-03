package com.cc.idle.game.config.conf.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigHeaderDTO {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 英文名称，用于反射
     */
    public String fieldName;
    /**
     * 中文注释
     */
    private String comment;
}
