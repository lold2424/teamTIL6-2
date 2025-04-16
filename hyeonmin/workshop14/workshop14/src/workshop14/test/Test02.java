package workshop14.test;

import workshop14.common.JDBCTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test02 {
    public static void main(String[] args) {
        String sql = """
		            SELECT PDSUBNAME, PDCOST, PDPRICE
		            FROM PRODUCT
		            WHERE 
		                (PDNAME = 'TV' AND PDCOST > (
		                    SELECT MIN(PDCOST) FROM PRODUCT WHERE PDNAME = 'TV'
		                ))
		                OR
		                (PDNAME = 'CELLPHONE' AND PDCOST < (
		                    SELECT MAX(PDCOST) FROM PRODUCT WHERE PDNAME = 'CELLPHONE'
		                ))
		        """;

        try (
                Connection conn = JDBCTemplate.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            System.out.printf("%-15s %-10s %-10s%n", "제품명", "제품원가", "제품가격");
            System.out.println("---------------------------------------------");

            while (rs.next()) {
                String name = rs.getString("PDSUBNAME");
                int cost = rs.getInt("PDCOST");
                int price = rs.getInt("PDPRICE");

                System.out.printf("%-15s %-10d %-10d%n", name, cost, price);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
