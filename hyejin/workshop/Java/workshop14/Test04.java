package com.workshop14;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Test04 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/bootcamp_db";
		String user = "bootcamp";
		String password = "0000";

		String sql = """
				    INSERT INTO DISCARDED_PRODUCT
				        (PDNO, PDNAME, PDSUBNAME, FACTNO, PDDATE, PDCOST, PDPRICE, PDAMOUNT, DISCARDED_DATE)
				    SELECT p.PDNO, p.PDNAME, p.PDSUBNAME, p.FACTNO, p.PDDATE, p.PDCOST, p.PDPRICE, p.PDAMOUNT, ?
				    FROM PRODUCT p
				    JOIN FACTORY f ON p.FACTNO = f.FACTNO
				    WHERE f.FACLOC = 'CHANGWON'
				""";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false); // 트랜잭션 시작
			pstmt.setDate(1, Date.valueOf(LocalDate.now()));

			int result = pstmt.executeUpdate();
			conn.commit();

			System.out.println(result + "개의 데이터가 정상적으로 INSERT 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
