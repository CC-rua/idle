package com.cc.idle.game.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(value = "com.cc.idle.game.*.api")
@MapperScan(value = "com.cc.idle.game.user.mybaits.generator.mapper")
public class GameUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameUserApplication.class, args);
    }
}
