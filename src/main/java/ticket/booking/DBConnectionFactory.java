package ticket.booking;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Class to make connections to the MySQL Database.
public class DBConnectionFactory {
    // Declaring the connection parameters.
	private static final String DATABASE = "ticket_booking";
	private static final String USER = "user23";
	private static final String PASSWORD = "thispassword";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE + "?autoReconnect=true&useSSL=false";

	// Method to get a connection object.
	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch(SQLException e) {
			throw new RuntimeException("Error connecting to database", e);
		}
	}
}