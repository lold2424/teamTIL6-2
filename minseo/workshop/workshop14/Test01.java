package workshop.workshop14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static workshop.JDBCTemplate.*;

public class Test01 {
	public static void main(String[] args) {
		String sql = "SELECT p.PDNAME, p.PDSUBNAME, f.FACNAME, s.STONAME, IFNULL(s.STAMOUNT, 0) AS STAMOUNT"
				+ " FROM PRODUCT as p JOIN FACTORY f USING(FACTNO) JOIN STORE s USING(PDNO)"
				+ " WHERE f.FACLOC = 'SEOUL' AND (s.STAMOUNT IS NULL OR s.STAMOUNT = 0)";

		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			System.out.println("제품카테고리 제품명 공장명 판매점명 판매점재고수량");
			while (rs.next()) {
				String pdname = rs.getString("PDNAME");
				String pdsubname = rs.getString("PDSUBNAME");
				String facname = rs.getString("FACNAME");
				String stoname = rs.getString("STONAME");
				int stamount = rs.getInt("STAMOUNT");

				System.out.println(pdname + pdsubname + facname + stoname + stamount);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}