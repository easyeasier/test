package wz.test.jdk.jdbc;

/**
 * Created by wangz on 17-12-5.
 */

import java.sql.*;

public class JDBCPGTest {

    public static void main(String[] argv) {

        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://172.31.107.35:5432/metadata", "postgres",
                    "metadata");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select count(1) from mt_data md where md.object_describe_api_name = " +
                    "'BpmInstance' and " +
                    "md" +
                    ".tenant_id = '54821'");

            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

}