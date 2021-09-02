package com.lc.controller.feign;

import com.lc.service.GitHubFeign;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author：大漠知秋
 * @Description：使用 Feign 访问 Github 查询 API
 * @CreateDate：2:42 PM 2018/10/24
 */
@RestController
public class GitHubController {


    @Resource
    private GitHubFeign gitHubFeign;

    @RequestMapping(value = "/search/repositories222")
    String searchRepo(@RequestParam("q") String q) {

        return gitHubFeign.searchRepo(q);
    }


}
