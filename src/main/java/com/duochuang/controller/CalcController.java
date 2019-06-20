/***********************************************
 * File Name: CalcController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 06 2019 15:21
 ***********************************************/

package com.duochuang.controller;

import com.duochuang.service.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/calc")
public class CalcController {

    @Autowired
    private CalcService calcService;

    @RequestMapping(value = "/add/{a}/{b}")
    @ResponseBody
    public String add(@PathVariable("a")String a,@PathVariable("b") String b){
        return calcService.add(a,b);
    }
}
