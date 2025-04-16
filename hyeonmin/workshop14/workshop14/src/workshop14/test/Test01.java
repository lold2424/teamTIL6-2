package workshop14.test;

import workshop14.common.JDBCTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Test01 {

    public static void main(String[] args) {
        String sql = """
	            SELECT P.PDNAME, P.PDSUBNAME, F.FACLOC, S.STONAME, 
	                   IFNULL(S.STAMOUNT, 0) AS STOREAMOUNT
	            FROM PRODUCT P
	            JOIN FACTORY F ON P.FACTNO = F.FACTNO
	            JOIN STORE S ON P.PDNO = S.PDNO
	            WHERE F.FACLOC = 'SEOUL' AND (S.STAMOUNT IS NULL OR S.STAMOUNT = 0)
	        """;

        try (
                Connection conn = JDBCTemplate.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            System.out.printf("%-10s %-10s %-10s %-10s %-10s%n", "제품카테고리", "제품명", "공장명", "판매점명", "판매점재고수량");
            System.out.println("-----------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10s %-15s %-10s %-15s %-10d%n",
                        rs.getString("PDNAME"),
                        rs.getString("PDSUBNAME"),
                        rs.getString("FACLOC"),
                        rs.getString("STONAME"),
                        rs.getInt("STOREAMOUNT"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
