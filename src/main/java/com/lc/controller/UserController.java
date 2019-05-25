package com.lc.controller;

import com.lc.service.UserService;
import com.lc.util.SizeofUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userController")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/insertTeamData")
    public Object insertTeamData()throws Exception{
        List list = new ArrayList();
        for(int i=0;i<2000;i++){
            Map map = new HashMap();
            map.put("id",i+" ");
            map.put("name","第"+i+"个人");
            list.add(map);
        }
        System.out.println(SizeofUtil.sizeof(list));
            userService.insertUsers(list);
            return list;
        }






}
