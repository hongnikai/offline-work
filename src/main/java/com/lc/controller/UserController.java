package com.lc.controller;

import com.lc.service.UserService;
import com.lc.util.MysqlPager;
import com.lc.util.SizeofUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public Object insertTeamData() throws Exception {
        List list = new ArrayList();
        for (int i = 0; i < 2000; i++) {
            Map map = new HashMap();
            map.put("id", i + " ");
            map.put("name", "第" + i + "个人");
            list.add(map);
        }
        System.out.println(SizeofUtil.sizeof(list));
        userService.insertUsers(list);
        return list;
    }


    /**
     * 返回新闻选项卡
     *
     * @param model
     * @return
     */
    @RequestMapping("/news")
    public String news(Model model, HttpServletRequest req) {
        MysqlPager mysqlPager = new MysqlPager();
        return "";
    }

    /**
     * 测试数据库悲观锁
     */
    @RequestMapping("/testSelectSock")
    public void testSelectSock() {
        userService.selectForUpdateSock();
    }

}
