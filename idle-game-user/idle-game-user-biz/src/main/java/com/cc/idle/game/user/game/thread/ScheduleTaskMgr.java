package com.cc.idle.game.user.game.thread;

import com.cc.idle.framework.common.util.collection.ordered.OrderedArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 计划任务管理器，为了让任务运行在指定
 */
@Component
@Slf4j
public class ScheduleTaskMgr implements CommandLineRunner {
    //loop任务分组精度
    private final int serverTickGroupAccuracy = 1000;
    //配置的主循环间隔为 200 ms
    private final int serverTickInterval = 200;
    /*
     * 计划任务分组数量，每个任务注册时会被分配到指定的组中，被分配到的组跟需要执行的时间有关，每个tick只需要找循环组中的一个组执行
     * 防止每一tick都需要判断所有任务是否需要执行
     */
    private final int loopGroupCount = 10;
    //主循环步数，每次执行完一个tick后，step+1意味着当前应该在 majorGroupList 中取用哪个组
    private final AtomicInteger step;
    //spring配置的线程池
    private final ThreadPoolTaskExecutor asyncServiceExecutor;
    //任务分组
    private final Map<Integer, OrderedArrayList<_AScheduleTask>> majorGroupMap;
    /**
     * 保持主循环的锁，防止多个线程同时执行
     */
    private ReentrantLock lock;

    public ScheduleTaskMgr(ThreadPoolTaskExecutor asyncServiceExecutor) {
        this.asyncServiceExecutor = asyncServiceExecutor;
        lock = new ReentrantLock();
        majorGroupMap = new HashMap<>();
        step = new AtomicInteger(0);
    }

    public void start() {
        while (true) {
            //等待20ms后再次启动
            try {
                Thread.sleep(serverTickInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            doThings();
        }
    }

    public void doThings() {
        lock.lock();
        try{
            int curStep = this.step.incrementAndGet();
            OrderedArrayList<_AScheduleTask> list = majorGroupMap.getOrDefault(curStep % loopGroupCount,
                    new OrderedArrayList<>(_AScheduleTask.comparable));

            for (_AScheduleTask scheduleTask : list.getElements()) {
                try {
                    //使用线程池为每一个 majorLoop 单独开启任务
                    asyncServiceExecutor.submit(scheduleTask);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            list.getElements().clear();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 注册执行的任务
     *
     * @param task
     */
    public void regTask(_AScheduleTask task) {
        lock.lock();
        try {
            long runTimeStamp = task.getRunTimeStamp();
            //调整精度
            long accuracyTime = runTimeStamp / serverTickGroupAccuracy;
            //计算任务应该被分配到的组
            int group = (int) (accuracyTime % loopGroupCount);
            OrderedArrayList<_AScheduleTask> groupList = majorGroupMap.get(group);
            if (groupList == null) {
                groupList = new OrderedArrayList<>(_AScheduleTask.comparable);
                majorGroupMap.put(group, groupList);
            }
            groupList.add(task);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        //启动主循环
        log.info("主循环启动");
        Thread thread = new Thread(this::start);
        thread.start();
    }
}
