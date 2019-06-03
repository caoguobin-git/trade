/***********************************************
 * File Name: IndexController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 23 05 2019 14:40
 ***********************************************/

package com.duochuang.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.duochuang.common.entity.UserEntity;
import com.duochuang.service.UserService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

@Controller
public class IndexController {

    Logger logger= LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/index/{username}")
    public String index(@PathVariable("username")String username, Model model) throws IOException {
        System.out.println(username);
        model.addAttribute("username", username);

        Connection connect = Jsoup.connect("https://cdn-rili.jin10.com/data/2019/0524/economics.json?_="+new Date().getTime()).header("referer", "https://rili.jin10.com/?date=20190524").ignoreContentType(true);

        Connection.Response execute = connect.execute();
        String body = execute.body();
        System.out.println(body);
        ObjectMapper objectMapper=new ObjectMapper();
        LinkedList linkedList = objectMapper.readValue(body, LinkedList.class);
        for (Object o : linkedList) {
            LinkedHashMap map= (LinkedHashMap) o;
            map.forEach((k,v)->{
                System.out.print(k+"  =  ");
                System.out.println(v);
            });
        }
        model.addAttribute("economicsBody",linkedList);

        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/getUserByUsername/{username}")
    @ResponseBody
    public UserEntity getUserByUsername(@PathVariable(value = "username")String username){
        UserEntity userEntity = userService.findByUsername(username);
        logger.error("前端获取得用户姓名为{}",username);
        return userEntity;
    }

}
