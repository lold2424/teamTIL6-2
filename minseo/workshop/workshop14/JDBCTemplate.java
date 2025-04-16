package workshop;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
    public static Connection getConnection() {
        Connection conn = null;
        Properties prop = new Properties();

        try(InputStream input = JDBCTemplate.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input==null) {
                throw new RuntimeException("db.properties파일이 없어");
            }

            prop.load(input);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            conn.setAutoCommit(false); // 트랜잭션 수동 처리
            if(!conn.isClosed()) {
                System.out.println("연결중이야");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public static void close(AutoCloseable ac) {
        try {
            if (ac != null) ac.close();
        } catch (Exception e) {}
    }

    public static void commit(Connection con) {
        if (con!=null) {
            try {
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void rollback(Connection con) {
        if (con!=null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}