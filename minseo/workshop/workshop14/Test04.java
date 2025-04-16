package workshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static workshop.JDBCTemplate.*;

public class Test04 {
	public static void main(String[] args) {
		String sql = "INSERT INTO DISCARDED_PRODUCT (PDNO, PDNAME, PDSUBNAME, FACTNO, PDDATE, PDCOST, PDPRICE, PDAMOUNT, DISCARDED_DATE)"
				+ " SELECT PDNO, PDNAME, PDSUBNAME, FACTNO, PDDATE, PDCOST, PDPRICE, PDAMOUNT, SYSDATE()"
				+ " FROM PRODUCT WHERE FACTNO = (SELECT FACTNO FROM FACTORY WHERE FACLOC = 'CHANGWON')";

		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			int res = pstmt.executeUpdate();
			if (res > 0) {
				conn.commit();
				System.out.println(res+"개의 데이터가 정상적으로 INSERT 되었습니다.");
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
