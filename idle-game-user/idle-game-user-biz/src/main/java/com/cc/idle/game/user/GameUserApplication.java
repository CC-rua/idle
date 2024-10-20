package com.cc.idle.game.user;

import com.cc.idle.game.user.game.thread.MajorLoopMgr;
import jakarta.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.cc.idle")
@EnableDiscoveryClient
@EnableFeignClients(value = "com.cc.idle.game.*.api")
@MapperScan(value = "com.cc.idle.game.user.mybaits.generator.mapper")
public class GameUserApplication implements CommandLineRunner {
    @Resource
    private MajorLoopMgr majorLoopMgr;

    public static void main(String[] args) {
        SpringApplication.run(GameUserApplication.class, args);
    }

    @Override
    public void run(String... args) {
        majorLoopMgr.run();
    }
}
