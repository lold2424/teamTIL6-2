package workshop14.test;

import workshop14.common.JDBCTemplate;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class Test04 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmtSelect = null;
        PreparedStatement pstmtInsert = null;
        ResultSet rs = null;

        try {
            conn = JDBCTemplate.getConnection();

            String selectSql = """
	                select pdno, pdname, pdsubname, factno, pddate, pdcost, pdprice, pdamount
	                from product
	                where factno in (
	                    select factno from factory where facloc = 'CHANGWON'
	                )
	            """;

            pstmtSelect = conn.prepareStatement(selectSql);
            rs = pstmtSelect.executeQuery();

            String insertSql = """
	                insert into discarded_product
	                (pdno, pdname, pdsubname, factno, pddate, pdcost, pdprice, pdamount, discarded_date)
	                values (?, ?, ?, ?, ?, ?, ?, ?, ?)
	            """;

            pstmtInsert = conn.prepareStatement(insertSql);

            int count = 0;

            while (rs.next()) {

                pstmtInsert.setInt(1, rs.getInt("pdno"));
                pstmtInsert.setString(2, rs.getString("pdname"));
                pstmtInsert.setString(3, rs.getString("pdsubname"));
                pstmtInsert.setString(4, rs.getString("factno"));
                pstmtInsert.setDate(5, rs.getDate("pddate"));
                pstmtInsert.setDouble(6, rs.getDouble("pdcost"));
                pstmtInsert.setDouble(7, rs.getDouble("pdprice"));
                pstmtInsert.setInt(8, rs.getInt("pdamount"));
                pstmtInsert.setDate(9, Date.valueOf(LocalDate.now())); // 현재 날짜로 설정

                pstmtInsert.executeUpdate();
                count++;
            }

            JDBCTemplate.Commit(conn);
            System.out.println(count + "개의 데이터가 정상적으로 INSERT 되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            JDBCTemplate.Rollback(conn);
        } finally {
            JDBCTemplate.Close(rs);
            JDBCTemplate.Close(pstmtSelect);
            JDBCTemplate.Close(pstmtInsert);
            JDBCTemplate.Close(conn);
        }
    }
}
