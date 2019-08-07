/***********************************************
 * File Name: TradeFollowTest
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 07 08 2019 下午 2:09
 ***********************************************/

package com.duochuang.test;

import com.fxcm.external.api.transport.FXCMLoginProperties;
import com.fxcm.external.api.transport.GatewayFactory;
import com.fxcm.external.api.transport.IGateway;
import com.fxcm.external.api.transport.listeners.IGenericMessageListener;
import com.fxcm.external.api.transport.listeners.IStatusMessageListener;
import com.fxcm.external.api.util.MessageGenerator;
import com.fxcm.fix.NotDefinedException;
import com.fxcm.fix.posttrade.ClosedPositionReport;
import com.fxcm.fix.posttrade.PositionReport;
import com.fxcm.fix.trade.ExecutionReport;
import com.fxcm.fix.trade.OrderSingle;
import com.fxcm.messaging.ISessionStatus;
import com.fxcm.messaging.ITransportable;

public class TradeFollowTest {
    public static void main(String[] args) {
        IGateway iGateway=GatewayFactory.createGateway();
        FXCMLoginProperties properties=new FXCMLoginProperties("701172924", "7723", "Demo", "http://www.fxcorporate.com/Hosts.jsp");
        iGateway.registerGenericMessageListener(new IGenericMessageListener() {
            @Override
            public void messageArrived(ITransportable iTransportable) {
                if (iTransportable instanceof ExecutionReport){
                    ExecutionReport report= (ExecutionReport) iTransportable;
                    System.out.println(report);
                }
            }
        });
        iGateway.registerStatusMessageListener(new IStatusMessageListener() {
            @Override
            public void messageArrived(ISessionStatus iSessionStatus) {
            }
        });
        try {
            iGateway.login(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }


        iGateway.requestTradingSessionStatus();
        iGateway.requestAccounts();
        iGateway.requestOpenOrders();
        iGateway.requestOpenPositions();
        iGateway.requestClosedPositions();

        new Thread(new Runnable() {
            @Override
            public void run() {
                IGateway gateway1= GatewayFactory.createGateway();
                gateway1.registerGenericMessageListener(new IGenericMessageListener() {
                    @Override
                    public void messageArrived(ITransportable iTransportable) {
                        if (iTransportable instanceof ClosedPositionReport){
                            return;
                        }
                        if (iTransportable instanceof PositionReport){
                            PositionReport positionReport= (PositionReport) iTransportable;
                            if (positionReport.getPosMaintRptID().equals("3")){
                                System.out.println(positionReport);
                                OrderSingle orderSingle = null;
                                try {
                                    String symbol=positionReport.getInstrument().getSymbol();
                                    System.out.println(symbol);
                                    orderSingle = MessageGenerator.generateMarketOrder("1001252140", positionReport.getPositionQty().getQty(), positionReport.getPositionQty().getSide(), symbol, "caoguobin test");
                                } catch (NotDefinedException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    String s = iGateway.sendMessage(orderSingle);
                                    System.out.println(s);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
                gateway1.registerStatusMessageListener(new IStatusMessageListener() {
                    @Override
                    public void messageArrived(ISessionStatus iSessionStatus) {

                    }
                });
                FXCMLoginProperties properties1=new FXCMLoginProperties("701116547", "890128", "Demo", "http://www.fxcorporate.com/Hosts.jsp");
                try {
                    gateway1.login(properties1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                gateway1.requestTradingSessionStatus();
                gateway1.requestAccounts();
                gateway1.requestOpenOrders();
                gateway1.requestOpenPositions();
                gateway1.requestClosedPositions();
            }
        }).start();
    }
}
