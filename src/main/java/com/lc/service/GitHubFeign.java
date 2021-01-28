package com.lc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author：大漠知秋
 * @Description：使用 Feign 访问 Github 查询 API
 * @CreateDate：2:36 PM 2018/10/24
 */
@FeignClient(value = "baidu-client",name = "baidu-client",url = "https://api.github.com")
public interface GitHubFeign {

    @RequestMapping(value = "/repositories")
    String searchRepo(@RequestParam("q") String q);


}
