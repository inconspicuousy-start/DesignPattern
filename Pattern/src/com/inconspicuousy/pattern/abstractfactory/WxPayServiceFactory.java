package com.inconspicuousy.pattern.abstractfactory;

import com.inconspicuousy.pattern.simplefactory.PayService;
import com.inconspicuousy.pattern.simplefactory.WeiXinPayService;

/**
 * @author peng.yi
 */
public class WxPayServiceFactory implements BasePayServiceFactory {

    /**
     * 获取支付服务对象(提供规范, 实现由子工厂实现), 这里采用工厂方法创建对象
     */
    @Override
    public PayService createPayService() {
        return new WeiXinPayService();
    }
}
