package com.inconspicuousy.pattern.factorymethod;

import com.inconspicuousy.pattern.simplefactory.PayService;
import com.inconspicuousy.pattern.simplefactory.WeiXinPayService;

/**
 * 专门用于创建微信支付服务的工厂类
 *
 * @author inconspicuousy
 */
public class WeixinPayServiceFactory extends PayServiceFactory {
    @Override
    PayService createPayService() {
        return new WeiXinPayService();
    }
}
