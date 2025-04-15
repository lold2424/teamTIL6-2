package com.workshop14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test02 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/bootcamp_db";
		String user = "bootcamp";
		String password = "0000";

		String sql = """
				    SELECT PDSUBNAME, PDCOST, PDPRICE
				    FROM PRODUCT
				    WHERE (PDNAME = 'TV' AND PDCOST > (SELECT MIN(PDCOST) FROM PRODUCT WHERE PDNAME = 'TV'))
				       OR (PDNAME = 'CELLPHONE' AND PDCOST < (SELECT MAX(PDCOST) FROM PRODUCT WHERE PDNAME = 'CELLPHONE'))
				""";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			System.out.printf("%-15s%-15s%-15s\n", "품명", "제품원가", "제품가격");
			System.out.println("------------+-------------+-------------");

			while (rs.next()) {
				String name = rs.getString("PDSUBNAME");
				int cost = rs.getInt("PDCOST");
				int price = rs.getInt("PDPRICE");

				System.out.printf("%-15s%-15d%-15d\n", name, cost, price);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
