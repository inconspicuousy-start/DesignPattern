package com.inconspicuousy.pattern.abstractfactory;

import com.inconspicuousy.pattern.simplefactory.PayService;

/**
 *
 * 商店, 具体的测试类
 * @author peng.yi
 */
public class Story {

    public static void main(String[] args) {

        // 商店采用支付宝并采用短信的方式进行通知
        BasePayServiceFactory aliPayServiceFactory = new AliPayServiceFactory();
        PayService payService = aliPayServiceFactory.createPayService();
        payService.doPay();
        BaseNotifyService notifyService = aliPayServiceFactory.createNotifyService(BasePayServiceFactory.TYPE_SMS);
        notifyService.doNotify();


    }

}
