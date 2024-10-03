package com.cc.idle.framework.common.util.vo;

import lombok.Data;

@Data
public class ConfigExcelHeaderDTO {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 英文名称
     */
    public String fieldName;
    /**
     * 中文注释
     */
    private String comment;
}
