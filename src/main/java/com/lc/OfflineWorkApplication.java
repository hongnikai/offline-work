package com.lc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@MapperScan({"com.lc.dao","com.lc.mapper"})
public class OfflineWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfflineWorkApplication.class, args);
    }

}
