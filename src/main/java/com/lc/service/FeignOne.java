package com.lc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "feign-client-one")
public interface FeignOne {

    @RequestMapping("/")
    String home();


}
