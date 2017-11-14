package wz.test.jdk.jdbc;

import java.sql.*;

/**
 * Created by wangz on 17-11-11.
 */
public class JDBCTransaction {

    public static void main(String[] args) {


        transaction();

    }

    /**
     * 事务测试:插入两条数据
     * 正常:两条正常插入
     * 异常1:插入一条后抛出错误,回滚,查看
     * 异常2:插入两条后抛出错误,回滚
     */
    public static void transaction() {
        Connection conn = getConn();
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        String insertSQL1 = "update student set name='hehe' where id = 1";
        String insertSQL2 = "insert into student (name,age) values ('wangz3', 20)";

        try {
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            conn.setAutoCommit(false);
            ps1 = getStatement(conn, insertSQL1);
            ps2 = getStatement(conn, insertSQL2);

            insertSuccess(ps1);
            Thread.sleep(1000 * 10);
            insertSuccess(ps2);  //情景1, 都正常
//            insertAndError(ps1);  //情景2 , 执行sql1后抛出异常
//            insertAndError(ps2);  //情景3 , 执行sql1后抛出异常



            conn.commit(); //都执行成功,以commit结尾,失败的话,以rollback结尾

        } catch (SQLException e) {
            System.out.println("....服务异常");
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("...人为异常");
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                ps1.close();
                ps2.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void insertSuccess(PreparedStatement statement) {
        try {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertAndError(PreparedStatement statement) {
        try {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            throw new RuntimeException("插入后失败");
        }
    }

    /**
     * 插入数据测试
     */
    public static void insert() {
        Connection conn = getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql1 = "insert into student (id,name,age) values (1,'wangz',18)";
        try {
            statement.execute(sql1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static PreparedStatement getStatement(Connection conn, String sql) {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }


    /**
     * 获取数据库链接测试
     *
     * @return
     */
    public static Connection getConn() {
        try {
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
        } catch (ClassNotFoundException e1) {
            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/wangz";    //JDBC的URL
        //调用DriverManager对象的getConnection()方法，获得一个Connection对象
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "root", "qweqwe");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
