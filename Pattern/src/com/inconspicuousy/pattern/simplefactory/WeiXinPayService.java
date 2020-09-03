package com.inconspicuousy.pattern.simplefactory;

/**
 * 微信支付
 * @author inconspicuousy
 */
public class WeiXinPayService implements PayService {

    @Override
    public void doPay() {
        System.out.println("使用微信支付");
    }
}
