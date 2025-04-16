package workshop.factory;

import static workshop.JDBCTemplate.*;

import java.sql.Connection;

public class FactoryBiz {
	private String driver;
	private String url;
	private String user;
	private String pass;
	public FactoryBiz() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FactoryBiz(String driver, String url, String user, String pass) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
	
	public void getFactoryAll() {
		try (Connection conn = getConnection()) {
			new FactoryDAO().getFactoryAll(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
