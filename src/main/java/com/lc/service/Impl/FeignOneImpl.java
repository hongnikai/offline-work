package com.lc.service.Impl;

import com.lc.service.FeignOne;

public class FeignOneImpl implements FeignOne {
    @Override
    public String home() {

        String message = " feign is good ";

        System.out.println(message);

        return message;
    }
}
