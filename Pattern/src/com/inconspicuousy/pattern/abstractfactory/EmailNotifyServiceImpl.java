package com.inconspicuousy.pattern.abstractfactory;

/**
 * @author peng.yi
 */
public class EmailNotifyServiceImpl implements BaseNotifyService {
    /**
     * 通知服务
     */
    @Override
    public void doNotify() {
        System.out.println("邮件通知服务");
    }
}
