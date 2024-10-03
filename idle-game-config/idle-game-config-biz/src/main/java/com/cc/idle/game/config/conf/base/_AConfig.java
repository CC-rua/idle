package com.cc.idle.game.config.conf.base;



import com.cc.idle.framework.common.conf.base.annotation.GameConfig;
import com.cc.idle.game.config.vo.ConfigMateDataVo;
import com.cc.idle.game.config.vo.ConfigRawDataVo;

import java.util.List;
import java.util.stream.Collectors;

public interface _AConfig {
    _AConfigLoader<?> getConfigLoader();

    Long getId();

    default String getConfigName() {
        GameConfig annotation = this.getClass().getAnnotation(GameConfig.class);
        if (annotation == null) {
            return null;
        }
        return annotation.fileName();
    }

    default List<ConfigHeaderDTO> getHeaderDTOs() {
        return getConfigLoader()
                .getConfigHeaderMap()
                .values().stream()
                .map(ConfigHeader::getHeaderDTO)
                .toList();
    }

    default ConfigRawDataVo makeRawData() {
        ConfigRawDataVo rawData = new ConfigRawDataVo();
        for (ConfigHeader header : getConfigLoader().getConfigHeaderMap().values()) {
            try {
                ConfigMateDataVo mateData = new ConfigMateDataVo();
                mateData.setId(header.getId());
                Object field = header.getField().get(this);
                if (field == null) {
                    mateData.setValue("");
                } else if (field instanceof List<?> listField) {
                    //是一个数组
                    String listStr = listField.stream().map(Object::toString).collect(Collectors.joining(";"));
                    mateData.setValue(listStr);
                } else {
                    mateData.setValue(String.valueOf(field));
                }
                rawData.getMetaDataList().add(mateData);
            } catch (Exception e) {
                System.out.println("配置文件中有无法转化成 string 的字段 {}" + header);

            }
        }
        return rawData;
    }

    default List<ConfigRawDataVo> getRawData() {
        return getConfigLoader().getConfigMap().values().stream()
                .map(_AConfig::makeRawData).toList();
    }

}
