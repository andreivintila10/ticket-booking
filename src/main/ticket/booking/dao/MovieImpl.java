package main.ticket.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.ticket.booking.DBConnectionFactory;
import main.ticket.booking.pojo.Movie;

public class MovieImpl implements MovieDAO {

	@Override
	public List<Movie> getAllMovies() {
		Connection connection = DBConnectionFactory.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM `movies`");

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

	@Override
	public Movie getMovie(int id) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM `movies` WHERE `id` = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

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

	@Override
	public Movie getMovieByTitle(String title) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM `movies` WHERE `title` = ?");
			preparedStatement.setString(1, title);
			resultSet = preparedStatement.executeQuery();

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

	@Override
	public boolean insertMovie(Movie movie) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO `movies` VALUES (NULL, ?, ?, ?, ?)");
			preparedStatement.setString(1, movie.getTitle());
			preparedStatement.setString(2, movie.getGenre());
			preparedStatement.setInt(3, movie.getYear());
			preparedStatement.setDouble(4, movie.getRating());
			
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
	public boolean updateMovie(Movie movie) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("UPDATE `movies` SET `id` = ?, `title` = ?, `genre` = ?, `year` = ?, `rating` = ?");
			preparedStatement.setInt(1, movie.getId());
			preparedStatement.setString(2, movie.getTitle());
			preparedStatement.setString(3, movie.getGenre());
			preparedStatement.setInt(4, movie.getYear());
			preparedStatement.setDouble(5, movie.getRating());
			
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
	public boolean deleteMovie(Movie movie) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("DELETE FROM `movies` WHERE `id` = ?");
			preparedStatement.setInt(1, movie.getId());

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