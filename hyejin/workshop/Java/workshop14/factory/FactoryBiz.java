package com.workshop14.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class FactoryBiz {
	private String url = "jdbc:mysql://localhost:3306/bootcamp_db";
	private String user = "bootcamp";
	private String pass = "0000";

	public FactoryBiz() {
	}

	public void getFactoryAll() {
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			new FactoryDAO().getFactoryAll(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
