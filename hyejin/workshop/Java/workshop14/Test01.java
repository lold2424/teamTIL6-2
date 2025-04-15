package com.workshop14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test01 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/bootcamp_db";
		String user = "bootcamp";
		String password = "0000"; // 비밀번호는 환경에 따라 변경

		String query = """
				    SELECT
				        p.PDNAME,
				        p.PDSUBNAME,
				        f.FACNAME,
				        s.STONAME,
				        IFNULL(s.STAMOUNT, 0) AS STAMOUNT
				    FROM
				        PRODUCT p
				    JOIN FACTORY f ON p.FACTNO = f.FACTNO
				    JOIN STORE s ON p.PDNO = s.PDNO
				    WHERE
				        f.FACLOC = 'SEOUL'
				        AND (s.STAMOUNT IS NULL OR s.STAMOUNT = 0)
				""";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			System.out.printf("%-15s%-15s%-20s%-15s%-10s\n", "제품카테고리", "제품명", "공장명", "판매점명", "판매점재고수량");
			System.out.println("--------------------------------------------------------------------------");

			while (rs.next()) {
				String pdname = rs.getString("PDNAME");
				String pdsubname = rs.getString("PDSUBNAME");
				String facname = rs.getString("FACNAME");
				String stoname = rs.getString("STONAME");
				int stamount = rs.getInt("STAMOUNT"); // IFNULL로 인해 NULL은 이미 0 처리됨

				System.out.printf("%-15s%-15s%-20s%-15s%-10d\n", pdname, pdsubname, facname, stoname, stamount);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
