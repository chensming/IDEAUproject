package sql;

import java.sql.*;

import static java.lang.Class.forName;

public class sqltest {
    public static void main(String[] args) throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/test";//?useSSL=true
        final String driver = "com.mysql.jdbc.Driver";
        final String user = "root";
        final String password = "root";

        try {
            Class.forName(driver);
            Connection c = DriverManager.getConnection(url, user, password);
            Statement s = c.createStatement();


            s.execute("insert into teamsports value('kobe','50米跑', 7.00)");

            s.close();
            c.close();

            System.out.println("操作数据库 teamsports 完成");
        } catch (ClassNotFoundException ex) {
            System.err.println("异常: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}




