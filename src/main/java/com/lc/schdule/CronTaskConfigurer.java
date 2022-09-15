package com.lc.schdule;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author lc 2022/7/19 11:06 AM
 */
@Slf4j
@Component
@Data
public class CronTaskConfigurer implements SchedulingConfigurer {

    private ScheduledTaskRegistrar scheduledTaskRegistrar;

  private static HashMap<String, ScheduledTask> scheduledChannel = new HashMap<>();
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        this.scheduledTaskRegistrar = scheduledTaskRegistrar;
    }

    public ScheduledTaskRegistrar getScheduledTaskRegistrar() {
        return scheduledTaskRegistrar;
    }

    public void setScheduledTaskRegistrar(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        this.scheduledTaskRegistrar = scheduledTaskRegistrar;
    }

    @Bean
    public Executor cronTaskExecutor(){
        ThreadFactory cronTaskExecutor = new ThreadFactoryBuilder().setNameFormat("cron-task-%d").build();
        return Executors.newScheduledThreadPool(10,cronTaskExecutor);
    }

    public void addCronTask(String workflowId, String cron, TreeSet treeSet){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        Trigger trigger = triggerContext -> {
            if(true){
                return null;
            }
            CronTrigger cronTrigger = new CronTrigger(cron);
            return cronTrigger.nextExecutionTime(triggerContext);
        };
        TriggerTask triggerTask = new TriggerTask(runnable,trigger);
        ScheduledTask scheduledTask = scheduledTaskRegistrar.scheduleTriggerTask(triggerTask);
        scheduledTaskRegistrar.addTriggerTask(triggerTask);
        scheduledChannel.put(workflowId,scheduledTask);
    }

    public void deleteCronTask(String workflowId){
        ScheduledTask scheduledTask = scheduledChannel.get(workflowId);
        if(Objects.nonNull(scheduledTask)){
            scheduledTask.cancel();
        }
    }


}
