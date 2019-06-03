/***********************************************
 * File Name: SocketController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 31 05 2019 10:31
 ***********************************************/

package com.duochuang.controller;

import com.duochuang.common.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.LinkedList;

@Controller
@RequestMapping("/socket")
public class SocketController {

//用于用户连接之后持续发送市场数据
//    static {
//        new Thread(() -> new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println(new Date().toString());
//                try {
//                    WebSocketServer.sendInfo("yilan-market: "+new Date().toLocaleString(), "yilan-market");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, 0, 2000)).start();
//    }

    //页面请求
    @GetMapping("/index")
    public String index() {
        return "socket";
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/push/{cid}/{message}")
    public JsonResult pushToWeb(@PathVariable String cid, @PathVariable("message") String message) {
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonResult("发送成功了");
    }

    @ResponseBody
    @RequestMapping(value = "/getUserList")
    public JsonResult getUserList(){
        LinkedList<String> webSocketSet = WebSocketServer.getWebSocketSet();
        return new JsonResult(webSocketSet);
    }

}
