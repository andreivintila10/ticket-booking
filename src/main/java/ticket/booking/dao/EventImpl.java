package ticket.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ticket.booking.DBConnectionFactory;
import ticket.booking.pojo.Event;

public class EventImpl implements EventDAO {

	@Override
	public List<Event> getAllEvents() {
		Connection connection = DBConnectionFactory.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM `events`");

			Event event;
			List<Event> events = new ArrayList<Event>();
			while (resultSet.next()) {
				event = extractEventFromResultSet(resultSet);
				events.add(event);
			}

			return events;

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
	public Event getEvent(int id) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM `events` WHERE `id` = ?");
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			Event event;
			if (resultSet.next()) {
				event = extractEventFromResultSet(resultSet);
				return event;
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
	public int insertEvent(Event event) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO `events` VALUES (NULL, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, event.getMovieId());
			preparedStatement.setInt(2, event.getScreenId());
			preparedStatement.setTimestamp(3, event.getDateTimestamp());
			preparedStatement.setDouble(4, event.getTicketPrice());
			preparedStatement.setInt(5, event.getTicketsAvailable());

			int hasSaved = preparedStatement.executeUpdate();

			if (hasSaved == 1) {
				resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					return resultSet.getInt(1);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { resultSet.close(); } catch (Exception e) { /* ignored */ }
			try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
			try { connection.close(); } catch (Exception e) { /* ignored */ }
		}

		return 0;
	}

	@Override
	public boolean updateEvent(Event event) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("UPDATE `events` SET `id` = ?, `movie_id` = ?, `screen_id` = ?, `date` = ?, `ticket_price` = ?, `tickets_available` = ?");
			preparedStatement.setInt(1, event.getId());
			preparedStatement.setInt(2, event.getMovieId());
			preparedStatement.setInt(3, event.getScreenId());
			preparedStatement.setTimestamp(4, event.getDateTimestamp());
			preparedStatement.setDouble(5, event.getTicketPrice());
			preparedStatement.setInt(6, event.getTicketsAvailable());

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
	public boolean deleteEvent(Event event) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("DELETE FROM `events` WHERE `id` = ?");
			preparedStatement.setInt(1, event.getId());

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

	public Event extractEventFromResultSet(ResultSet resultSet) throws SQLException {
		Event event = new Event();

		event.setId(resultSet.getInt("id"));
		event.setMovieId(resultSet.getInt("movie_id"));
		event.setScreenId(resultSet.getInt("screen_id"));
		event.setDateFromTimestamp(resultSet.getTimestamp("date"));
		event.setTicketPrice(resultSet.getDouble("ticket_price"));
		event.setTicketsAvailable(resultSet.getInt("tickets_available"));

		return event;
	}

}
