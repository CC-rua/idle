package com.cc.idle.game.user.game.thread;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class MajorLoop extends _AScheduleTask {
    private final ScheduleTaskMgr scheduleTaskMgr;

    /**
     * 需要执行的任务
     */
    private final List<_AGameTask> taskList;

    public MajorLoop(ScheduleTaskMgr scheduleTaskMgr) {
        this.scheduleTaskMgr = scheduleTaskMgr;
        taskList = new ArrayList<>();
    }

    public void regisTask(_AGameTask task) {
        lock.lock();
        try {
            taskList.add(task);
        } finally {
            lock.unlock();
        }
    }

    public void start0() {
        scheduleTaskMgr.regTask(this);
        start();
    }


    @Override
    public void run() {
        lock.lock();
        try {
            for (_AGameTask task : taskList) {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            taskList.clear();
            try {
                tick();
            } catch (Exception e) {
                e.printStackTrace();
            }

            scheduleTaskMgr.regTask(this);
        } finally {
            lock.unlock();
        }
    }

    protected abstract void tick();

    protected abstract void start();


}
