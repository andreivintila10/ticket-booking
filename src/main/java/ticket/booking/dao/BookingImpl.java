package ticket.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ticket.booking.DBConnectionFactory;
import ticket.booking.pojo.Booking;

public class BookingImpl implements BookingDAO {

    // Method to get all bookings ever made.
	@Override
	public List<Booking> getAllBookings() {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
		    // Create statement and execute MySQL query.
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM `bookings`");

			// Retrieve all bookings.
			Booking booking;
			List<Booking> bookings = new ArrayList<Booking>();
			while (resultSet.next()) {
				booking = extractBookingFromResultSet(resultSet);
				bookings.add(booking);
			}

			return bookings;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { resultSet.close(); } catch (Exception e) { /* ignored */ }
			try { statement.close(); } catch (Exception e) { /* ignored */ }
			try { connection.close(); } catch (Exception e) { /* ignored */ }
		}

		return null;
	}

	// Method to get a booking by the booking id.
	@Override
	public Booking getBooking(int id) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
		    // Prepare statement and execute MySQL query.
			preparedStatement = connection.prepareStatement("SELECT * FROM `bookings` WHERE `id` = ?");
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			// If the booking was found then retrieve it.
			if (resultSet.next()) {
				Booking booking = extractBookingFromResultSet(resultSet);
				return booking;
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

	// Method to insert a booking.
	@Override
	public int insertBooking(Booking booking) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
		    // Prepare statement and execute MySQL update.
			preparedStatement = connection.prepareStatement("INSERT INTO `bookings` VALUES (NULL, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, booking.getEventId());
			preparedStatement.setString(2, booking.getGuestName());
			preparedStatement.setInt(3, booking.getTicketsBooked());
			preparedStatement.setDouble(4, booking.getTicketsValueTotal());

			int hasSaved = preparedStatement.executeUpdate();

			// If insertion was successful then retrieve the booking id (booking reference number).
			if (hasSaved == 1) {
				resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next())
					return resultSet.getInt(1);
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

	// Method to update a booking.
	@Override
	public boolean updateBooking(Booking booking) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
		    // Prepare statement and execute MySQL update.
			preparedStatement = connection.prepareStatement("UPDATE `bookings` SET `id` = ?, `event_id` = ?, `guest_name` = ?, `tickets_booked` = ?, `tickets_value_total` = ?");
			preparedStatement.setInt(1, booking.getId());
			preparedStatement.setInt(2, booking.getEventId());
			preparedStatement.setString(3, booking.getGuestName());
			preparedStatement.setInt(4, booking.getTicketsBooked());
			preparedStatement.setDouble(5, booking.getTicketsValueTotal());

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

	// Method to delete a booking.
	@Override
	public boolean deleteBooking(Booking booking) {
	    // Establish a connection.
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
		    // Prepare statement and execute MySQL update.
			preparedStatement = connection.prepareStatement("DELETE FROM `bookings` WHERE `id` = ?");
			preparedStatement.setInt(1, booking.getId());

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

	// Method to facilitate extracting booking information.
	// Throws SQLException
	public Booking extractBookingFromResultSet(ResultSet resultSet) throws SQLException {
		Booking booking = new Booking();

		booking.setId(resultSet.getInt("id"));
		booking.setEventId(resultSet.getInt("event_id"));
		booking.setGuestName(resultSet.getString("guest_name"));
		booking.setTicketsBooked(resultSet.getInt("tickets_booked"));
		booking.setTicketsValueTotal(resultSet.getDouble("tickets_value_total"));

		return booking;
	}

}
