package com.log.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.log.parser.EventDetailsModel;

public class HSQLDAO {

	private static final String createTableSQL = (new StringBuffer("create table if not exists eventdetails (").append("  eventid varchar(20) NOT NULL,")
			.append("  eventduration BIGINT NOT NULL,").append("  type varchar(20),").append( "  host varchar(20),")
			.append("  alert boolean NOT NULL," + "PRIMARY KEY (eventid)   );")).toString();

	private static final String INSERT_USERS_SQL = (new StringBuffer("INSERT INTO eventdetails")
			.append("(eventid, eventduration, type, host, alert) VALUES ").append(" (?, ?, ?, ?, ?);")).toString();


	/*
	 * public static void main(String[] args) throws SQLException { HSQLDAO
	 * createTableExample = new HSQLDAO(); createTableExample.createTable(); }
	 */

	public void createTable() throws SQLException {

		Connection connection = null;
		Statement statement = null;

		try {
			connection = JDBCUtils.getConnection();
			statement = connection.createStatement();
			statement.execute(createTableSQL);
			System.out.println("Table created");
		} catch (Exception e) {
			System.out.println("Exception:"+e.getMessage());

		}
		finally
		{
			connection.close();
			statement.close();
		}
	}

	public void insertRecord(HashMap<String, EventDetailsModel> events) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement =null;

		try{
			connection = JDBCUtils.getConnection();

			preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			connection.setAutoCommit(false);

			EventDetailsModel eventObj = new  EventDetailsModel();
			for (Map.Entry<String, EventDetailsModel> pair : events.entrySet()) {
				eventObj= pair.getValue();

				preparedStatement.setString(1,eventObj.getEventId());
				preparedStatement.setLong(2,eventObj.getEventDuration());
				preparedStatement.setString(3,eventObj.getType());
				preparedStatement.setString(4,eventObj.getHost());
				preparedStatement.setBoolean(5,eventObj.isAlert());

				preparedStatement.addBatch();
			}
			int[] result = preparedStatement.executeBatch();
			System.out.println("The number of logs inserted to DB: "+ result.length);
			connection.commit();
		}catch(Exception e){
			e.printStackTrace();
			connection.rollback();
		} finally{
			if(preparedStatement!=null)
				preparedStatement.close();
			if(connection!=null)
				connection.close();
		}

	}

}
