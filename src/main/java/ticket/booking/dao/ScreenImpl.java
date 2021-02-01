package ticket.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ticket.booking.DBConnectionFactory;
import ticket.booking.pojo.Screen;

public class ScreenImpl implements ScreenDAO {

    // Method to get all screens.
	@Override
	public List<Screen> getAllScreens() {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
		    // Create statement and execute MySQL query.
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM `screens`");

			// Retrieve all screens.
			Screen screen;
			List<Screen> screens = new ArrayList<Screen>();
			while (resultSet.next()) {
				screen = extractScreenFromResultSet(resultSet);
				screens.add(screen);
			}

			return screens;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { resultSet.close(); } catch (Exception e) { /* ignored */ }
		    try { statement.close(); } catch (Exception e) { /* ignored */ }
		    try { connection.close(); } catch (Exception e) { /* ignored */ }
		}

		return null;
	}

	// Method to get screen by id
	@Override
	public Screen getScreen(int id) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
		    // Prepare statement and execute MySQL query.
			preparedStatement = connection.prepareStatement("SELECT * FROM `screens` WHERE `id` = ?");
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			// If the screen was found then retrieve it.
			if (resultSet.next()) {
				Screen screen = extractScreenFromResultSet(resultSet);
				return screen;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { resultSet.close(); } catch (Exception e) { /* ignored */ }
		    try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		    try { connection.close(); } catch (Exception e) { /* ignored */ }
		}

		return null;
	}

	// Method to insert a screen.
	@Override
	public boolean insertScreen(Screen screen) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
		    // Prepare statement and execute MySQL update.
			preparedStatement = connection.prepareStatement("INSERT INTO `screens` VALUES (NULL, ?, ?)");
			preparedStatement.setInt(1, screen.getCinemaId());
			preparedStatement.setInt(2, screen.getCapacity());

			int hasSaved = preparedStatement.executeUpdate();

			// If insertion was successful then return true.
			if (hasSaved == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		    try { connection.close(); } catch (Exception e) { /* ignored */ }
		}

		return false;
	}

	// Method to update a screen.
	@Override
	public boolean updateScreen(Screen screen) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
		    // Prepare statement and execute MySQL update.
			preparedStatement = connection.prepareStatement("UPDATE `screens` SET `id` = ?, `cinema_id` = ?, `capacity` = ?");
			preparedStatement.setInt(1, screen.getId());
			preparedStatement.setInt(2, screen.getCinemaId());
			preparedStatement.setInt(3, screen.getCapacity());

			int hasUpdated = preparedStatement.executeUpdate();

			// If update was successful then return true.
			if (hasUpdated == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		    try { connection.close(); } catch (Exception e) { /* ignored */ }
		}

		return false;
	}

	// Method to delete a screen.
	@Override
	public boolean deleteScreen(Screen screen) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
		    // Prepare statement and execute MySQL update.
			preparedStatement = connection.prepareStatement("DELETE FROM `screens` WHERE `id` = ?");
			preparedStatement.setInt(1, screen.getId());

			int hasDeleted = preparedStatement.executeUpdate();

			// If delete was successful then return true.
			if (hasDeleted == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
			try { connection.close(); } catch (Exception e) { /* ignored */ }
		}

		return false;
	}

	// Method to facilitate extracting cinema information.
    // Throws SQLException
	public Screen extractScreenFromResultSet(ResultSet resultSet) throws SQLException {
		Screen screen = new Screen();

		screen.setId(resultSet.getInt("id"));
		screen.setCinemaId(resultSet.getInt("cinema_id"));
		screen.setCapacity(resultSet.getInt("capacity"));

		return screen;
	}
}
