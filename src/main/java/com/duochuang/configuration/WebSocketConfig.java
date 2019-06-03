/***********************************************
 * File Name: WebSocketConfig
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 31 05 2019 10:18
 ***********************************************/

package com.duochuang.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
