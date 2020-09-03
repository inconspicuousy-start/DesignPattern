package com.inconspicuousy.pattern.simplefactory;

/**
 * 提供简单支付工厂类， 为用户选择指定的支付方式
 *
 * 简单工厂又叫静态工厂
 * @author inconspicuousy
 */
public class PayServiceFactory {

    /**
     * 根据二维码链接获取到实际的支付方式
     *
     * 什么是简单工厂模式？
     * 实际上就是根据用户的某个行为创建指定的对象，
     * 将创建对象的方式作集中处理， 每次如果要对支付方式扩展，只需要针对简单工厂类进行修改，
     * 调用者不需要做修改， 符合OCP原则
     *
     * @param qrCodeUrl 用户选择的支付方式， 用来区分支付宝还是微信
     * @return 返回实际的支付方式
     */
    public static PayService getPayService(String qrCodeUrl) {

        if (qrCodeUrl.startsWith("alipay")) {
            return new AliPayService();
        }

        if (qrCodeUrl.startsWith("weixin")) {
            return new WeiXinPayService();
        }

        System.out.println("当前没有可用的支付方式");

        return null;
    }


}
