package workshop.workshop14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static workshop.JDBCTemplate.*;

public class Test02 {
	public static void main(String[] args) {
		String sql = "SELECT PDSUBNAME, PDCOST, PDPRICE FROM PRODUCT"
				+ " WHERE (PDNAME = 'TV' AND PDCOST > (SELECT MIN(PDCOST) FROM PRODUCT WHERE PDNAME = 'TV'))"
				+ " OR (PDNAME = 'CELLPHONE' AND PDCOST < (SELECT MAX(PDCOST) FROM PRODUCT WHERE PDNAME = 'CELLPHONE'))";

		try (Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			System.out.println("품명 제품원가 제품가격");
			while (rs.next()) {
				String name = rs.getString("PDSUBNAME");
				int cost = rs.getInt("PDCOST");
				int price = rs.getInt("PDPRICE");

				System.out.println(name + cost + price);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
