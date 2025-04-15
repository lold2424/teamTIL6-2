package com.workshop14.factory;

import java.sql.*;

public class FactoryDAO {
	public void getFactoryAll(Connection conn) throws SQLException {
		String sql = "SELECT * FROM FACTORY";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

			System.out.printf("%-10s%-20s%-15s\n", "공장번호", "공장명", "공장위치");
			System.out.println("-------------------------------------------");

			while (rs.next()) {
				System.out.printf("%-10s%-20s%-15s\n", rs.getString("FACTNO"), rs.getString("FACNAME"),
						rs.getString("FACLOC"));
			}
		}
	}
}
