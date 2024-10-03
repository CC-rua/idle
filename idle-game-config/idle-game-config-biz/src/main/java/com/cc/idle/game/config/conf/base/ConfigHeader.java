package com.cc.idle.game.config.conf.base;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Data
@Slf4j
public class ConfigHeader {
    /**
     * 为一个类的字段编号 大多的时候为字段在类中的顺序，从0开始
     */
    private Integer id;
    /**
     * 从json中取出的数据
     */
    public ConfigHeaderDTO headerDTO;
    /**
     * 类中的字段
     */
    public Field field;

    /**
     * 验证表头的完整性
     */
    public boolean validate() {
        if (!ObjectUtil.isAllNotEmpty(id, headerDTO, field)) {
            log.error("表头不完整,id:{},header:{},field:{}", id, headerDTO, field);
            return false;
        }
        return true;
    }
}
