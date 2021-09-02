//package com.lc.service;
//
//import org.springframework.cloud.openfeign.FeignClient;
//
///**
// * @author lc 4/16/21 3:57 PM
// */
//@FeignClient("micro-onlineloan-test")
//public class OpenfeignPostClient {
//
//    //启动类加上
//    //@EnableDiscoveryClient
//    //@EnableFeignClients("com.hzbank.micro")
//
//    /**
//     * get 用 @RequestParam("operNo") String operNo
//     * 形式穿参数 不能用实体类传参
//     */
////    @GetMapping("/applyApproval/queryApplyConditionList?" +
////            "prodSeq=2&startTime=2019-7&endTime=2020-9")
////    Object queryApplyConditionList(@RequestParam ApplyconditionRequest applyconditionRequest
////    );
//
//    /**
//     * post 可以用实体类传参
//     */
////    @PostMapping("/applyApproval/testPost")
////    String testPost(@RequestBody ApplyconditionRequest applyconditionRequest);
////
//
//
//
//}
