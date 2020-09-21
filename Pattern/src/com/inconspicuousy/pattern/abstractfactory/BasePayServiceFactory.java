package com.inconspicuousy.pattern.abstractfactory;

import com.inconspicuousy.pattern.simplefactory.PayService;

import java.util.Objects;

/**
 * 抽象工厂基础支付类
 * 抽象工厂其实是简单工厂和工厂方法模式的合体
 *  - 简单工厂实际上就是将获取单一对象的方法封装起来由工厂统一处理(就比如服装厂专门生产服装)
 *  - 工厂方法实际上就是基类接口定制获取单一对象的方法规范, 具体生产对象的方式交给下级工厂实现类处理(就比如服装厂定制服装的样式, 具体服装的生产交给下级工厂处理)
 *  - 抽象工厂实际上就是两者的多元化合体, 只是创建的对象由一个转变为多个(比如现在的服装厂不但生产服装, 我还要生产鞋子等)
 *
 *  本次例子中, 我们还是以支付为例, 一般支付服务商不但要提供支付功能, 并且还要提供支付成功获取失败的通知功能(多元化生产对象)
 * @author peng.yi
 */
public interface BasePayServiceFactory {

    String TYPE_SMS = "SMS";
    String TYPE_EMAIl = "EMAIL";


    /**
     * 获取通知服务对象, 这里采用简单工厂的方式用于创建对象
     */
    default BaseNotifyService createNotifyService(String type) {
        if (Objects.isNull(type)) {
            return null;
        }
        switch (type) {
            case TYPE_SMS:
                return new SmsNotifyServiceImpl();
            case TYPE_EMAIl:
                return new EmailNotifyServiceImpl();
            default:
                return null;
        }
    }

    /** 获取支付服务对象(提供规范, 实现由子工厂实现), 这里采用工厂方法创建对象 */
    PayService createPayService();





}
