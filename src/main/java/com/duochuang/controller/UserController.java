/***********************************************
 * File Name: UserController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 23 05 2019 14:39
 ***********************************************/

package com.duochuang.controller;

import com.google.common.base.Strings;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/login/{username}/{password}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String login(HttpServletRequest request, @RequestParam(value = "id",required = false) String id, @PathVariable("username") String username, @PathVariable("password") String password) {
        if (Strings.isNullOrEmpty(id)){
            return username+"  "+password;
        }
            return id + "  " + username + "  " + password;
    }

}
