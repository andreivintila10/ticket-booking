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

    // Method to get all cinemas.
	@Override
	public List<Cinema> getAllCinemas() {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
		    // Create statement and execute MySQL query.
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM `cinema`");

			// Retrieve all cinemas.
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

	// Method to get a cinema by id.
	@Override
	public Cinema getCinema(int id) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
		    // Prepare statement and execute MySQL query.
			preparedStatement = connection.prepareStatement("SELECT * FROM `cinema` WHERE `id` = ?");
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			// If the cinema was found then retrieve it.
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

	// Method to insert a cinema.
	@Override
	public boolean insertCinema(Cinema cinema) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
		    // Prepare statement and execute MySQL update.
			preparedStatement = connection.prepareStatement("INSERT INTO `cinema` VALUES (NULL, ?, ?)");
			preparedStatement.setString(1, cinema.getName());
			preparedStatement.setString(2, cinema.getAddress());

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

	// Method to update a cinema.
	@Override
	public boolean updateCinema(Cinema cinema) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
		    // Prepare statement and execute MySQL update.
			preparedStatement = connection.prepareStatement("UPDATE `cinema` SET `id` = ?, `name` = ?, `address` = ?");
			preparedStatement.setInt(1, cinema.getId());
			preparedStatement.setString(2, cinema.getName());
			preparedStatement.setString(3, cinema.getAddress());

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

	// Method to delete a cinema.
	@Override
	public boolean deleteCinema(Cinema cinema) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
		    // Prepare statement and execute MySQL update.
			preparedStatement = connection.prepareStatement("DELETE FROM `cinema` WHERE `id` = ?");
			preparedStatement.setInt(1, cinema.getId());

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
	public Cinema extractCinemaFromResultSet(ResultSet resultSet) throws SQLException {
		Cinema cinema = new Cinema();

		cinema.setId(resultSet.getInt("id"));
		cinema.setName(resultSet.getString("name"));
		cinema.setAddress(resultSet.getString("address"));

		return cinema;
	}

}
