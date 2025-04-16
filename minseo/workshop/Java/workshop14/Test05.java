package workshop;

import static workshop.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test05 {
	public static void main(String[] args) {
		String sql = "DELETE FROM PRODUCT WHERE PDNO IN (SELECT PDNO FROM DISCARDED_PRODUCT)";

		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			int res = pstmt.executeUpdate();
			if (res > 0) {
				conn.commit();
				System.out.println(res+"개의 데이터가 정상적으로 DELETE 되었습니다.");
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
