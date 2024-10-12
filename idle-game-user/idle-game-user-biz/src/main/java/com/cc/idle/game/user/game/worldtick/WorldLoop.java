package com.cc.idle.game.user.game.worldtick;


import com.cc.idle.game.user.game.PlayerDomainMgr;
import com.cc.idle.game.user.game.thread.MajorLoop;
import com.cc.idle.game.user.game.thread.ScheduleTaskMgr;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * 服务器跳数循环
 */
@Component
@EnableScheduling
public class WorldLoop extends MajorLoop {
    @Resource
    private PlayerDomainMgr playerDomainMgr;

    public WorldLoop(ScheduleTaskMgr scheduleTaskMgr) {
        super(scheduleTaskMgr);
    }

    @Override
    protected void tick() {
        playerDomainMgr.tick();
    }

    @Override
    protected void start() {
    }

    @Override
    public long getSleepTime() {
        return 60;
    }
}
