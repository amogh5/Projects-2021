package com.log.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

	private static final String jdbcURL = "jdbc:hsqldb:file:db-data/database";
	//    ..\\java-hsql-assignment\\logFile.txt

	private static final String jdbcUsername = "SA";
	private static final String jdbcPassword = "";

	public static Connection getConnection() throws Exception {

		Connection connection = null;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (Exception e) {
			throw e;
		}
		
		try {
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}


}



