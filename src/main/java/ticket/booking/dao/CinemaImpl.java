package ticket.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ticket.booking.DBConnectionFactory;
import ticket.booking.pojo.Cinema;

public class CinemaImpl implements CinemaDAO {

	@Override
	public List<Cinema> getAllCinemas() {
		Connection connection = DBConnectionFactory.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM `cinema`");

			Cinema cinema;
			List<Cinema> cinemas = new ArrayList<Cinema>();
			while (resultSet.next()) {
				cinema = extractCinemaFromResultSet(resultSet);
				cinemas.add(cinema);
			}

			return cinemas;

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
	public Cinema getCinema(int id) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM `cinema` WHERE `id` = ?");
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Cinema cinema = extractCinemaFromResultSet(resultSet);
				return cinema;
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
	public boolean insertCinema(Cinema cinema) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO `cinema` VALUES (NULL, ?, ?)");
			preparedStatement.setString(1, cinema.getName());
			preparedStatement.setString(2, cinema.getAddress());

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
	public boolean updateCinema(Cinema cinema) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("UPDATE `cinema` SET `id` = ?, `name` = ?, `address` = ?");
			preparedStatement.setInt(1, cinema.getId());
			preparedStatement.setString(2, cinema.getName());
			preparedStatement.setString(3, cinema.getAddress());

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
	public boolean deleteCinema(Cinema cinema) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("DELETE FROM `cinema` WHERE `id` = ?");
			preparedStatement.setInt(1, cinema.getId());

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
	
	public Cinema extractCinemaFromResultSet(ResultSet resultSet) throws SQLException {
		Cinema cinema = new Cinema();

		cinema.setId(resultSet.getInt("id"));
		cinema.setName(resultSet.getString("name"));
		cinema.setAddress(resultSet.getString("address"));

		return cinema;
	}

}
