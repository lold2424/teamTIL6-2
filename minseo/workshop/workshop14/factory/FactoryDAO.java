package workshop.factory;

import static workshop.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FactoryDAO {
	public void getFactoryAll(Connection conn) {
		String sql = "SELECT FACTNO, FACNAME, FACLOC FROM FACTORY";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			System.out.println("공장번호 공장명 공장위치");

			while (rs.next()) {
				String FACTNO = rs.getString("FACTNO");
				String FACNAME = rs.getString("FACNAME");
				String FACLOC = rs.getString("FACLOC");

				System.out.println(FACTNO + FACNAME + FACLOC);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
