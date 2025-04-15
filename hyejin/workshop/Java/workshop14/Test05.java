package com.workshop14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test05 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/bootcamp_db";
		String user = "bootcamp";
		String password = "0000";

		String sql = """
				    DELETE FROM PRODUCT
				    WHERE PDNO IN (SELECT PDNO FROM DISCARDED_PRODUCT)
				""";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			int result = pstmt.executeUpdate();
			conn.commit();

			System.out.println(result + "개의 데이터가 정상적으로 DELETE 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
