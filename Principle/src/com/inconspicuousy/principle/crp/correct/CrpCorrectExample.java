package com.inconspicuousy.principle.crp.correct;

interface DBConnection {
    String getConnection();
}

class MySqlDBConnection implements DBConnection{
    @Override
    public String getConnection() {
        return "MySQL数据库连接";
    }
}

class OracleDBConnection implements DBConnection {
    @Override
    public String getConnection() {
        return "Oracle数据库连接";
    }
}

class ProductDao {
    // 采用组合的方式将 DBConnection 和 ProductDao 建立耦合关系
    private DBConnection dbConnection;

    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addProduct() {
        String dbConnection = this.dbConnection.getConnection();
        System.out.println("使用" + dbConnection + "添加了一条数据");
    }

}

/**
 * 合成复用原则正确示例
 *
 * @author peng.yi
 */
public class CrpCorrectExample {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        productDao.setDbConnection(new MySqlDBConnection());
        productDao.addProduct();
        productDao.setDbConnection(new OracleDBConnection());
        productDao.addProduct();
    }
}
