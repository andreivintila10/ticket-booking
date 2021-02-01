package ticket.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ticket.booking.DBConnectionFactory;
import ticket.booking.pojo.Movie;

public class MovieImpl implements MovieDAO {

    // Method to get all movies.
	@Override
	public List<Movie> getAllMovies() {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
		    // Create statement and execute MySQL query.
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM `movies`");

			// Retrieve all movies.
			Movie movie;
			List<Movie> movies = new ArrayList<Movie>();
			while (resultSet.next()) {
				movie = extractMovieFromResultSet(resultSet);
				movies.add(movie);
			}

			return movies;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { resultSet.close(); } catch (Exception e) { /* ignored */ }
		    try { statement.close(); } catch (Exception e) { /* ignored */ }
		    try { connection.close(); } catch (Exception e) { /* ignored */ }
		}

		return null;
	}

	// Method to get movie by id
	@Override
	public Movie getMovie(int id) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
		    // Prepare statement and execute MySQL query.
			preparedStatement = connection.prepareStatement("SELECT * FROM `movies` WHERE `id` = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			// If the movie was found then retrieve it.
			if (resultSet.next()) {
				Movie movie = extractMovieFromResultSet(resultSet);
				return movie;
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

	// Method to get movie by title
	@Override
	public Movie getMovieByTitle(String title) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
		    // Prepare statement and execute MySQL query.
			preparedStatement = connection.prepareStatement("SELECT * FROM `movies` WHERE `title` = ?");
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();

			// If the movie was found then retrieve it.
			if (resultSet.next()) {
				Movie movie = extractMovieFromResultSet(resultSet);
				return movie;
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

	// Method to insert a movie.
	@Override
	public boolean insertMovie(Movie movie) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
		    // Prepare statement and execute MySQL update.
			preparedStatement = connection.prepareStatement("INSERT INTO `movies` VALUES (NULL, ?, ?, ?, ?)");
			preparedStatement.setString(1, movie.getTitle());
			preparedStatement.setString(2, movie.getGenre());
			preparedStatement.setInt(3, movie.getYear());
			preparedStatement.setDouble(4, movie.getRating());
			
			// If insertion was successful then return true.
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

	// Method to update a movie.
	@Override
	public boolean updateMovie(Movie movie) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
		    // Prepare statement and execute MySQL update.
			preparedStatement = connection.prepareStatement("UPDATE `movies` SET `id` = ?, `title` = ?, `genre` = ?, `year` = ?, `rating` = ?");
			preparedStatement.setInt(1, movie.getId());
			preparedStatement.setString(2, movie.getTitle());
			preparedStatement.setString(3, movie.getGenre());
			preparedStatement.setInt(4, movie.getYear());
			preparedStatement.setDouble(5, movie.getRating());
			
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

	// Method to delete a movie.
	@Override
	public boolean deleteMovie(Movie movie) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
		    // Prepare statement and execute MySQL update.
			preparedStatement = connection.prepareStatement("DELETE FROM `movies` WHERE `id` = ?");
			preparedStatement.setInt(1, movie.getId());

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
	private Movie extractMovieFromResultSet(ResultSet resultSet) throws SQLException {
		Movie movie = new Movie();

		movie.setId(resultSet.getInt("id"));
		movie.setTitle(resultSet.getString("title"));
		movie.setGenre(resultSet.getString("genre"));
		movie.setYear(resultSet.getInt("year"));
		movie.setRating(resultSet.getDouble("rating"));

		return movie;
	}
}