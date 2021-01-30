package main.ticket.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.ticket.booking.DBConnectionFactory;
import main.ticket.booking.pojo.Screen;

public class ScreenImpl implements ScreenDAO {

	@Override
	public List<Screen> getAllScreens() {
		Connection connection = DBConnectionFactory.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM `screens`");

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

	@Override
	public Screen getScreen(int id) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM `screens` WHERE `id` = ?");
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

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

	@Override
	public boolean insertScreen(Screen screen) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO `screens` VALUES (NULL, ?, ?)");
			preparedStatement.setInt(1, screen.getCinemaId());
			preparedStatement.setInt(2, screen.getCapacity());

			int hasSaved = preparedStatement.executeUpdate();

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

	@Override
	public boolean updateScreen(Screen screen) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("UPDATE `screens` SET `id` = ?, `cinema_id` = ?, `capacity` = ?");
			preparedStatement.setInt(1, screen.getId());
			preparedStatement.setInt(2, screen.getCinemaId());
			preparedStatement.setInt(3, screen.getCapacity());

			int hasUpdated = preparedStatement.executeUpdate();

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

	@Override
	public boolean deleteScreen(Screen screen) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("DELETE FROM `screens` WHERE `id` = ?");
			preparedStatement.setInt(1, screen.getId());

			int hasDeleted = preparedStatement.executeUpdate();

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

	public Screen extractScreenFromResultSet(ResultSet resultSet) throws SQLException {
		Screen screen = new Screen();

		screen.setId(resultSet.getInt("id"));
		screen.setCinemaId(resultSet.getInt("cinema_id"));
		screen.setCapacity(resultSet.getInt("capacity"));

		return screen;
	}
}
