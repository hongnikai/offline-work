package com.lc.service.Impl;

import com.lc.service.GitHubFeign;
import org.springframework.stereotype.Component;

@Component
public class GitHubFeignImpl implements GitHubFeign {


    @Override
    public String searchRepo(String q) {

        System.out.println("aaaaaaaaaaaaa");

        return q;
    }
}
