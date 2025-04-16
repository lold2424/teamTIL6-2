package workshop14.test;

import workshop14.common.JDBCTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Test05 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCTemplate.getConnection();

            String deleteSql = """
                delete from product
                where pdno in (select pdno from discarded_product)
            """;

            pstmt = conn.prepareStatement(deleteSql);
            int deletedCount = pstmt.executeUpdate();

            JDBCTemplate.Commit(conn);
            System.out.println(deletedCount + "개의 데이터가 정상적으로 DELETE 되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
            JDBCTemplate.Rollback(conn);
        } finally {
            JDBCTemplate.Close(pstmt);
            JDBCTemplate.Close(conn);
        }
    }
}
