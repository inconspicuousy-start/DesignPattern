package com.inconspicuousy.pattern.abstractfactory;

/**
 * @author peng.yi
 */
public class SmsNotifyServiceImpl implements BaseNotifyService {

    /**
     * 通知服务
     */
    @Override
    public void doNotify() {
        System.out.println("短信通知服务");
    }
}
