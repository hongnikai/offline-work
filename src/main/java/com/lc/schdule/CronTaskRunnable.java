package com.lc.schdule;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.TreeSet;

/**
 * @author lc 2022/9/27 3:49 PM
 */
@Slf4j
@Data
@Component
public class CronTaskRunnable implements Runnable{

//    private TreeSet<TaskCronFlowDTO> treeSet;
    private TreeSet treeSet;//传递参数


    @Override
    public void run() {



    }


}
