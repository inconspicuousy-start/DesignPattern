package com.inconspicuousy.pattern.factorymethod;

import com.inconspicuousy.pattern.simplefactory.PayService;

/**
 * 要获取支付服务的商店客户端
 *
 * @author inconspicuousy
 */
public class Story {

    public static void main(String[] args) {
    //    这里的商店自己需要什么支付服务就创建对应的服务
        PayService payService = new WeixinPayServiceFactory().createPayService();
        // 使用微信支付
        payService.doPay();

        PayService payService1 = new AliPayServiceFactory().createPayService();
        // 使用支付宝支付
        payService1.doPay();
    }

}
