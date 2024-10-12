package com.cc.idle.game.user.game.thread;

import lombok.Getter;

import java.util.Comparator;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 各种游戏专用循环线程
 */
@Getter
public abstract class _AScheduleTask implements _AGameTask {
    /**
     * 创建任务时间
     */
    protected long createTimeStamp;
    /**
     * 任务执行排序方式
     */
    public static Comparator<_AScheduleTask> comparable = (t1, t2) -> (int) (t1.createTimeStamp - t2.createTimeStamp);

    protected ReentrantLock lock;


    public _AScheduleTask() {
        this.createTimeStamp = System.currentTimeMillis();
        this.lock = new ReentrantLock();
    }

    public long getRunTimeStamp() {
        return createTimeStamp + getCreateTimeStamp();
    }

    //计划执行时间
    public abstract long getSleepTime();
}
