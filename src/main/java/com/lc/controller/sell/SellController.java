package com.lc.controller.sell;

import com.lc.util.SizeofUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lc 4/19/21 4:08 PM
 */
@Controller
public class SellController {


    @RequestMapping("/insertTeamData")
    public String insertTeamData()throws Exception{
//        List list = new ArrayList();
//        for(int i=0;i<2000;i++){
//            Map map = new HashMap();
//            map.put("id",i+" ");
//            map.put("name","第"+i+"个人");
//            list.add(map);
//        }
//        System.out.println(SizeofUtil.sizeof(list));

        return "index.html";
    }



}
