package com.cc.idle.framework.rpc.core.decoder;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cc.idle.framework.rpc.core.annotation.FeignCleanNoneFieldResp;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * feign 客户端的自定义编码器
 * 自定义解码器
 */
public class CustomDecoder implements Decoder {

    private final SpringDecoder springDecoder;
    private final ObjectMapper objectMapper;

    public CustomDecoder(SpringDecoder springDecoder) {
        this.springDecoder = springDecoder;
        this.objectMapper = new ObjectMapper();
        // 设置忽略值为null的属性
        objectMapper.setSerializationInclusion(Include.NON_NULL);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        Class<?> clazz = getRawType(type);

        FeignCleanNoneFieldResp annotation = clazz.getAnnotation(FeignCleanNoneFieldResp.class);
        if (annotation == null) {
            // 没有注解，使用默认解码器
            return springDecoder.decode(response, type);
        } else {
            // 有注解，使用去除空字段后解码
            String responseBody = Util.toString(response.body().asReader());
            JSONObject jsonObject = JSONUtil.parseObj(responseBody);
            for (Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<String, Object> entry = iterator.next();
                //去除空串
                if (Objects.equals("", entry.getValue()) || ObjectUtil.isEmpty(entry.getValue())) {
                    iterator.remove();
                }
            }
            JavaType javaType = objectMapper.getTypeFactory().constructType(type);
            return objectMapper.readValue(jsonObject.toString(), javaType);
        }
    }

    /**
     * 获取类的原始类型
     *
     * @param type
     * @return
     */
    private Class<?> getRawType(Type type) {
        if (type instanceof Class<?>) {
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType parameterizedType) {
            return (Class<?>) parameterizedType.getRawType();
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type);
        }
    }
}
