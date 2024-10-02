package com.cc.idle.game.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GameConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameConfigApplication.class, args);
    }
}
