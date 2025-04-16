package workshop14.common;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// DB 연결 생성 , 자원 해제 (Connection, Statement, ResultSet), 트랜잭션 처리(Commit,Rollback)
public class JDBCTemplate {
    /// case1:  DB 연결하는 곳_ 실제 값 대입
//    public static Connection getConnection() {
//        Connection conn = null;
//        try {
//            String url = "jdbc:mysql://localhost:3306/my_emp";
//            String user = "mydb";
//            String password = "Jack1123@!";
//
//            conn = DriverManager.getConnection(url, user, password);
//
//            conn.setAutoCommit(false); //트랜잭션 수동 설정
//
//
//            if (!conn.isClosed()) {
//                System.out.println("연결 중이야");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
    /// case2: DB 연결하는 곳_ properties 파일로 로드하는 방법
    ///mywork -> myjava -> appjava -> classpath 확인하기 src로 되어있으면 경로가 src밑에 있어야함
    public static Connection getConnection() {
        Connection conn = null;
        Properties prop = new Properties();
        try(InputStream input = JDBCTemplate.class.getClassLoader().getResourceAsStream("db.properties")) {

            if(input == null) {
                throw new RuntimeException("db.properties 파일 없음");
            }
            prop.load(input);

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver); //jdk 버전 상관없이 리소스 로드할땐 반드시 명시
            conn = DriverManager.getConnection(url, user, password);

            conn.setAutoCommit(false); //트랜잭션 수동 설정


            if (!conn.isClosed()) {
                System.out.println("연결 중");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //1. DB close
    public static void Close(Connection con) {
        if(con != null) {
            try {
                con.close();
                // con = null; non-static 일때 명시
            } catch (SQLException e) {
                // e.printStackTrace
                System.out.println("데이터베이스 연결 닫기 오류 : "+e.getSQLState() + "\t"+ e.getMessage());
            }
        }
    }
    // 2.Statement DB close하는 곳
    public static void Close(Statement stmt) {
        if(stmt != null) {
            try {
                stmt.close();
                // con = null; non-static 일때 명시
            } catch (SQLException e) {
                // e.printStackTrace
                System.out.println("명령 오류 : "+e.getSQLState() + "\t"+ e.getMessage());
            }
        }
    }
    // 3. resultset close하는 곳
    public static void Close(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("쿼리 리턴 오류: "+e.getSQLState() + "\t"+ e.getMessage());
            }
        }
    }
    //4.트랜잭션 처리(Commit,Rollback)
    public static void Commit(Connection con) {
        if(con != null) {
            try {
                con.commit();
            } catch (SQLException e) {
                // e.printStackTrace
                System.out.println("데이터베이스 연결 닫기 오류 : "+e.getSQLState() + "\t"+ e.getMessage());
            }
        }
    }
    //4-2 rollback
    public static void Rollback(Connection con) {
        if(con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                // e.printStackTrace
                System.out.println("데이터베이스 연결 닫기 오류 : "+e.getSQLState() + "\t"+ e.getMessage());
            }
        }
    }

}
