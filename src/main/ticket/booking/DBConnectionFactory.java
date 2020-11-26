package main.ticket.booking;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {
	public static final String DATABASE = "ticket_booking";
	public static final String USER = "user23";
	public static final String PASSWORD = "thispassword";
	public static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE + "?autoReconnect=true&useSSL=false";

	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch(SQLException e) {
			throw new RuntimeException("Error connecting to database", e);
		}
	}
}