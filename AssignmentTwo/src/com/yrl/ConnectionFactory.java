package com.yrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL,DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		return conn;
	}
}
