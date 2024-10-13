package com.cc.idle.framework.config;


import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket 自动配置
 *
 * @author xingyu4j
 */
@AutoConfiguration
@EnableWebSocket // 开启 websocket
public class IdleGameWebSocketAutoConfiguration {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}