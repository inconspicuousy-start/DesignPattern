package com.inconspicuousy.principle.crp.error;

/** 数据库连接对象 */
class DBConnection {
    /** 获取到MySQl数据库连接 */
    public String getDbConnection() {
        return "MySQL 数据库连接";
    }
}

/** 这里为了直接获取到数据库连接, 直接采用继承的方式 */
class ProductDao extends DBConnection{
    public void addProduct() {
        String dbConnection = getDbConnection();
        System.out.println("使用" + dbConnection + "添加了一条数据");
    }
}

/**
 * 合成复用原则错误代码演示
 *
 * Composite Reuse Principle 合成复用原则
 * @author peng.yi
 */
public class CrpErrorExample {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        productDao.addProduct();
    }
}
