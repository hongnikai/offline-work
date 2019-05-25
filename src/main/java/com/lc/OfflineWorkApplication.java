package com.lc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lc.dao")
public class OfflineWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfflineWorkApplication.class, args);
    }

}
