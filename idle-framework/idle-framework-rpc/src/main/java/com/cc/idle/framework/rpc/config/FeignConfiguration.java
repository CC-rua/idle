package com.cc.idle.framework.rpc.config;

import com.cc.idle.framework.rpc.core.decoder.CustomDecoder;
import com.cc.idle.framework.rpc.core.decoder.ExtMappingJackson2HttpMessageConverter;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import feign.optionals.OptionalDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Slf4j
@AutoConfiguration
@SuppressWarnings("SpringComponentScan")
@EnableFeignClients("${idle.info.base-package}")
public class FeignConfiguration implements RequestInterceptor {


    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
    }

    // new一个form编码器，实现支持form表单提交
    // 注意这里方法名称，也就是bean的名称是什么不重要，
    // 重要的是返回类型要是 Encoder 并且实现类必须是 FormEncoder 或者其子类
    @Bean
    @Primary
    public Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
        return new SpringFormEncoder(new SpringEncoder(converters));
    }

    public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        final HttpMessageConverters httpMessageConverters
                = new HttpMessageConverters(new ExtMappingJackson2HttpMessageConverter());
        return () -> httpMessageConverters;
    }

    @Bean
    public Decoder feignDecoder(
            ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        // 自定义feign的解析器
        // OptionalDecoder(response,type) -> ResponseEntityDecoder(response,type) -> 自定义Decoder(response,type)
        CustomDecoder feignResponseDecoder = new CustomDecoder(new SpringDecoder(feignHttpMessageConverter(),
                customizers));
        ResponseEntityDecoder responseEntityDecoder = new ResponseEntityDecoder(feignResponseDecoder);
        return new OptionalDecoder(responseEntityDecoder);
    }

    @Bean
    @Primary
    RestTemplate customRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ExtMappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}
