package com.inconspicuousy.pattern.simplefactory;

/**
 * 支付宝支付
 * @author inconspicuousy
 */
public class AliPayService implements PayService {

    @Override
    public void doPay() {
        System.out.println("使用支付宝支付");
    }
}
