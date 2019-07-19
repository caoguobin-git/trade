/***********************************************
 * File Name: FxcmController
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 31 05 2019 17:01
 ***********************************************/

package com.duochuang.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxcm.external.api.transport.FXCMLoginProperties;
import com.fxcm.external.api.transport.GatewayFactory;
import com.fxcm.external.api.transport.IGateway;
import com.fxcm.fix.NotDefinedException;
import com.fxcm.fix.pretrade.MarketDataSnapshot;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FxcmController {
    static {
        IGateway iGateway= GatewayFactory.createGateway();
        iGateway.registerGenericMessageListener(iMessage -> {
            if (iMessage instanceof MarketDataSnapshot){
                MarketDataSnapshot marketDataSnapshot= (MarketDataSnapshot) iMessage;
                try {
                    ObjectMapper objectMapper=new ObjectMapper();
                    String sb = objectMapper.writeValueAsString(marketDataSnapshot);
                    WebSocketServer.sendInfo(sb, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
        iGateway.registerStatusMessageListener(iStatus->{

        });
        FXCMLoginProperties properties=new FXCMLoginProperties("701172924", "7723", "Demo", "http://www.fxcorporate.com/Hosts.jsp");


        try {
            iGateway.login(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

        iGateway.requestTradingSessionStatus();
        iGateway.requestAccounts();
        iGateway.requestOpenPositions();
        iGateway.requestClosedPositions();
        iGateway.requestOpenOrders();
    }
}
