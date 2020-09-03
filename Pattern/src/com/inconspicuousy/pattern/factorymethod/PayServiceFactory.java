package com.inconspicuousy.pattern.factorymethod;

import com.inconspicuousy.pattern.simplefactory.PayService;

/**
 * 父类抽象工厂
 * @author inconspicuousy
 */
public abstract class PayServiceFactory {

    /** 抽象方法用于子工厂具体实现 */
    abstract PayService createPayService();

}
