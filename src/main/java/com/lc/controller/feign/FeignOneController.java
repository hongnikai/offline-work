package com.lc.controller.feign;

import com.lc.service.FeignOne;

public class FeignOneController {

    private FeignOne feignOne;


    public String testHome(){
        // 调用feign,feign调用 eureka-client-one的api
        return feignOne.home();
    }

}
