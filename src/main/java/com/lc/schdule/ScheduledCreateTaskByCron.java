package com.lc.schdule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

/**
 * @author lc 2022/7/19 10:08 AM
 */
@Slf4j
public class ScheduledCreateTaskByCron {

    private static ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public static Map<String, ScheduledFuture<?>> getFutureMap() {
        return futureMap;
    }

    private static Map<String, ScheduledFuture<?>> futureMap = new HashMap<>();

    static {
        threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(100);
        threadPoolTaskScheduler.initialize();
    }

    public static Boolean isNull(String scheduledId) {
        ScheduledFuture<?> scheduledFuture = futureMap.get(scheduledId);
        if (Objects.isNull(scheduledFuture)) {
            return true;
        }
        return false;
    }

    /**
     * 创建定时任务
     */
    public static void createScheduled(String workflowId, String className, String cron) {
        log.info("创建定时任务 id={},className={},cron={}", workflowId, className, cron);
        if (futureMap.get(workflowId) != null) {
            stopScheduled(workflowId);
        }
        futureMap.put(workflowId, threadPoolTaskScheduler.schedule(Objects.requireNonNull(getObjectClass(className)), new CronTrigger(cron)));
    }

    /**
     * 创建定时任务
     */
    public static void createScheduled(String workflowId, Runnable runnable, String cron) {
        if(futureMap.get(workflowId) != null){
            stopScheduled(workflowId);
        }
        futureMap.put(workflowId,threadPoolTaskScheduler.schedule(Objects.requireNonNull(runnable),new CronTrigger(cron)));
    }

    /**
     * 停止定时任务
     */
    public static void stopScheduled(String workflowId) {
        if (futureMap.get(workflowId) != null) {
            while (!futureMap.get(workflowId).isDone()) {
                break;
            }
            log.info("删除 定时任务 id={}", workflowId);
            futureMap.get(workflowId).cancel(true);
            futureMap.remove(workflowId);
        }
    }

    private static Runnable getObjectClass(String className) {

        try {
            return (Runnable) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
