package com.lc.controller.feign;

import com.lc.entity.BlogModel;
import com.lc.service.BlogRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author lc 3/26/21 2:58 PM
 */
@RestController
@RequestMapping("/blog")
public class BlogController {


    private BlogRepository blogRepository;

    @PostMapping("/add")
    public String add(@RequestBody BlogModel blogModel) {
        blogRepository.save(blogModel);
        return "添加成功";
    }

    @GetMapping("/get/{id}")
    public Object getById(@PathVariable String id) {
        if (StringUtils.isEmpty(id))
            return "不存在这条数据";
        Optional<BlogModel> blogModelOptional = blogRepository.findById(id);
        if (blogModelOptional.isPresent()) {
            BlogModel blogModel = blogModelOptional.get();
            return blogModel;
        }
        return "异常";
    }

    @GetMapping("/get")
    public List<BlogModel> getAll() {
        Iterable<BlogModel> iterable = blogRepository.findAll();
        List<BlogModel> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

//    @GetMapping("/esget")
//    public void esget(){
//        GetRequest request = new GetRequest("index_test", "DkfxKHcBDAil241A0UJ4");
//        GetResponse response = client.get(request, RequestOptions.DEFAULT);
//        System.out.println(response.getSourceAsString());
//        System.out.println(response.toString());
//    }

}
