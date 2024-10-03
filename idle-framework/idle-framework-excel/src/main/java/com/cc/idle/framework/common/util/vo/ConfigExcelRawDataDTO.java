package com.cc.idle.framework.common.util.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConfigExcelRawDataDTO {
    private List<ConfigExcelMateDataDTO> metaDataList;

    public ConfigExcelRawDataDTO() {
        metaDataList = new ArrayList<>();
    }
}
