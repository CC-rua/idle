package com.cc.idle.framework.common.util.object;

import cn.hutool.core.util.ObjectUtil;
import com.cc.idle.framework.common.conf.base._IStringConvertible;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * CastUtil
 *
 * @author cc
 * @description
 * @since 2023/5/29 11:13
 */
@Slf4j
public class CastUtil {
    /**
     * 判定是否基本数据类型(包括包装类)
     *
     * @param type 类
     * @return 是否为基本数据类型
     */
    public static boolean isPrimitive(Class<?> type) {
        return type == boolean.class
                || type == Boolean.class
                || type == double.class
                || type == Double.class
                || type == float.class
                || type == Float.class
                || type == short.class
                || type == Short.class
                || type == int.class
                || type == Integer.class
                || type == long.class
                || type == Long.class
                || type == String.class
                || type == byte.class
                || type == Byte.class
                || type == char.class
                || type == Character.class
                || type.isEnum();
    }

    /**
     * 返回基本数据类型的空值
     *
     * @param type 类
     * @return 对应的空值
     */
    public static Object primitiveNull(Class<?> type) {
        if (type.equals(int.class) || type.equals(double.class) ||
                type.equals(short.class) || type.equals(long.class) ||
                type.equals(byte.class) || type.equals(float.class)) {
            return 0;
        }
        if (type.equals(boolean.class)) {
            return false;
        }
        return null;
    }

    /**
     * String类型转换成对应类型
     *
     * @param type  转换的类
     * @param value 值
     * @return 转换后的Object
     */

    @SuppressWarnings("unchecked")
    public static Object convert(Class<?> type, String value) {
        if (isPrimitive(type)) {
            if (ObjectUtil.isEmpty(value)) {
                return primitiveNull(type);
            }

            if (type.equals(int.class) || type.equals(Integer.class)) {
                return Integer.parseInt(value);
            } else if (type.equals(String.class)) {
                return value;
            } else if (type.equals(Double.class) || type.equals(double.class)) {
                return Double.parseDouble(value);
            } else if (type.equals(Float.class) || type.equals(float.class)) {
                return Float.parseFloat(value);
            } else if (type.equals(Long.class) || type.equals(long.class)) {
                return Long.parseLong(value);
            } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
                return Boolean.parseBoolean(value);
            } else if (type.equals(Short.class) || type.equals(short.class)) {
                return Short.parseShort(value);
            } else if (type.equals(Byte.class) || type.equals(byte.class)) {
                return Byte.parseByte(value);
            } else if (type.isEnum()) {
                //如果是枚举类型，则使用枚举的valueOf方法转化成枚举对象
                try {
                    return Enum.valueOf((Class<? extends Enum>) type, value);
                } catch (Exception e) {
                    throw new RuntimeException("枚举类型转换失败 type: " + type + "value: " + value);
                }
            }
            return value;
        } else {
            throw new RuntimeException("暂时不支持非原生");
        }
    }

    /**
     * 将字符串转化为指定类型
     *
     * @param type
     * @param valueStr
     * @return
     */
    public static Object convertObj(Class<?> type, String valueStr) {
        if (CastUtil.isPrimitive(type)) {
            return CastUtil.convert(type, valueStr);
        } else if (_IStringConvertible.class.isAssignableFrom(type)) {
            //该类实现了 _IStringConvertible 接口，可以由String转化成对象
            try {
                Constructor<?> constructor = type.getConstructor();
                constructor.setAccessible(true);
                if (valueStr != null && !valueStr.isEmpty()) {
                    _IStringConvertible fieldVal = (_IStringConvertible) constructor.newInstance();
                    fieldVal.convert(valueStr);
                    return fieldVal;
                }
            } catch (Exception e) {
                log.error("type：{} 无法转化成对象_IStringConvertible ", type, e);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Object convertObj(Field field, String valueStr) {
        Class<?> type = field.getType();
        //判断type 是 List或者 List的字类
        if (List.class.isAssignableFrom(type)) {
            try {
                Type genericType = field.getGenericType();
                Constructor<ArrayList<Object>> constructor = (Constructor<ArrayList<Object>>) type.getConstructor();
                //数组内的对象
                ArrayList<Object> list = constructor.newInstance();
                if (valueStr == null || valueStr.isEmpty()) {
                    return list;
                }
                String[] split = valueStr.split(";");
                for (String s : split) {
                    if (genericType instanceof ParameterizedType parameterizedType) {
                        // 获取实际的类型参数
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        Type actualTypeArgumentClass = actualTypeArguments[0];
                        list.add(CastUtil.convertObj((Class<?>) actualTypeArgumentClass, s));
                    }
                }
                return list;
            } catch (Exception e) {
                log.error("type：{} 无法转化成对象 array ", type, e);
                return null;
            }
        } else {
            return CastUtil.convertObj(field.getType(), valueStr);
        }
    }
}
