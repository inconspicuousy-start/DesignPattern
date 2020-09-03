package com.inconspicuousy.pattern.factorymethod;

import com.inconspicuousy.pattern.simplefactory.AliPayService;
import com.inconspicuousy.pattern.simplefactory.PayService;

/**
 * 创建支付宝支付服务的工厂类
 * @author inconspicuousy
 */
public class AliPayServiceFactory extends PayServiceFactory {
    @Override
    PayService createPayService() {
        return new AliPayService();
    }
}
