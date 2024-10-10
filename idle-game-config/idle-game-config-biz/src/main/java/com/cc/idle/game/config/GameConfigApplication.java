package com.cc.idle.game.config;


import com.cc.idle.game.config.conf.base.ConfigManager;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GameConfigApplication implements CommandLineRunner {
    @Resource
    private ConfigManager configManager;

    public static void main(String[] args) {
        SpringApplication.run(GameConfigApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        configManager.run();
    }
}
