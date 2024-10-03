package com.cc.idle.game.config.convert;


import com.cc.idle.framework.common.util.vo.ConfigExcelHeaderDTO;
import com.cc.idle.framework.common.util.vo.ConfigExcelMateDataDTO;
import com.cc.idle.framework.common.util.vo.ConfigExcelRawDataDTO;
import com.cc.idle.game.config.conf.base.ConfigHeaderDTO;
import com.cc.idle.game.config.vo.ConfigMateDataVo;
import com.cc.idle.game.config.vo.ConfigRawDataVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExcelDTOConvert {
    ExcelDTOConvert INSTANCE = Mappers.getMapper(ExcelDTOConvert.class);

    ConfigExcelHeaderDTO toDTO(ConfigHeaderDTO vo);

    List<ConfigExcelHeaderDTO> toHeaderList(List<ConfigHeaderDTO> vos);

    ConfigExcelMateDataDTO toDTO(ConfigMateDataVo vo);

    List<ConfigExcelMateDataDTO> toMateDataList(List<ConfigMateDataVo> vos);

    ConfigExcelRawDataDTO toDTO(ConfigRawDataVo vo);

    List<ConfigExcelRawDataDTO> toRawDataList(List<ConfigRawDataVo> vos);

}
