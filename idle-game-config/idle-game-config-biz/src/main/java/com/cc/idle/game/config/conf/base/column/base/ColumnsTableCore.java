package com.cc.idle.game.config.conf.base.column.base;

import cn.hutool.extra.spring.SpringUtil;
import com.cc.idle.framework.common.conf.base._IConstValueHelper;
import com.cc.idle.framework.common.conf.base.annotation.ColumnsTable;
import com.cc.idle.framework.common.conf.base.annotation.ColumnsTableFiled;
import com.cc.idle.framework.common.util.object.CastUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@Slf4j
@Component
public class ColumnsTableCore {
    @Resource
    private List<_IConstValueHelper> constValueHelperList;

    public void autoReadTable() {
        //获取继承的所有类
        for (_IConstValueHelper iConstValueHelper : constValueHelperList) {
            Class<? extends _IConstValueHelper> clazz = iConstValueHelper.getClass();
            ColumnsTable clazzAnnotation = clazz.getAnnotation(ColumnsTable.class);
            if (clazzAnnotation == null) {
                continue;
            }
            try {
                //config类
                Class<?> configClass = clazzAnnotation.configClass();

                Object configIns = SpringUtil.getBean(configClass);

                //获取getSn方法
                Method getColumnMethod = configClass.getMethod("get", Long.class);
                Method getFieldValueMethod = configClass.getMethod("getValue");

                //遍历helper类的field然后给每个field赋值
                for (Field helperField : clazz.getFields()) {
                    ColumnsTableFiled columnsTableFiled = helperField.getAnnotation(ColumnsTableFiled.class);
                    if (columnsTableFiled == null) {
                        continue;
                    }

                    helperField.setAccessible(true);
                    Object column = getColumnMethod.invoke(configIns, columnsTableFiled.sn());
                    if (column == null) {
                        continue;
                    }
                    //获取属性应该的值
                    String fieldValue = (String) getFieldValueMethod.invoke(column);
                    if (fieldValue == null) {
                        log.info("fieldValue is null filed:{}", helperField.getName());
                        continue;
                    }
                    Object castObj = CastUtil.convertObj(helperField, fieldValue);
                    helperField.set(iConstValueHelper, castObj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
