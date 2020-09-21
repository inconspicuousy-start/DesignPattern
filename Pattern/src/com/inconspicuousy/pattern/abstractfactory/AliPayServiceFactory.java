package com.inconspicuousy.pattern.abstractfactory;

import com.inconspicuousy.pattern.simplefactory.AliPayService;
import com.inconspicuousy.pattern.simplefactory.PayService;

/**
 * @author peng.yi
 */
public class AliPayServiceFactory implements BasePayServiceFactory {

    /**
     * 获取支付服务对象(提供规范, 实现由子工厂实现), 这里采用工厂方法创建对象
     */
    @Override
    public PayService createPayService() {
        return new AliPayService();
    }

}
