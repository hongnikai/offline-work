package com.lc.learn.java.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author lc 2/7/21 3:59 PM
 */
@Slf4j
@Configuration
@EnableAsync
public class TaskExecutorLearn {

    @Bean("taskExecutor")
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(10);
        //设置最大线程数
        executor.setMaxPoolSize(20);
        //设置队列容量
        executor.setQueueCapacity(200);
        //设置线程活跃时间(秒)
        executor.setKeepAliveSeconds(60);
        //设置默认线程
        executor.setThreadNamePrefix("async-");
        /*
         *设置拒绝策略，当pool已经达到max size的时候，采取CALLER_RUNS
         *不在新线程中执行任务，而是调用者所在的线程来执行
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        log.info("初始化线程池: "+executor.getThreadNamePrefix());
        return executor;
    }



}
