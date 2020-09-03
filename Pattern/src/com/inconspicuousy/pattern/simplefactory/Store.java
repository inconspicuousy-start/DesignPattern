package com.inconspicuousy.pattern.simplefactory;

/**
 * 店铺就是当前的客户端， 用来为用户提供二维码链接
 *
 * 测试简单工厂
 * @author inconspicuousy
 */
public class Store {

    public static void main(String[] args) {

        String qrCodeUrl = "alipay://xxx";
        // 所有的店铺都只是通过二维码获取到支付方式
       // 当需要扩展支付方式时时不需要商店作出改变， 改变简单工厂就行
        PayService payService = PayServiceFactory.getPayService(qrCodeUrl);
        if (payService != null) {
            // 使用支付宝支付
            payService.doPay();
        }
    }

}
