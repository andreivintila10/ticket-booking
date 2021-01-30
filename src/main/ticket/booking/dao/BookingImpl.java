package main.ticket.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.ticket.booking.DBConnectionFactory;
import main.ticket.booking.pojo.Booking;

public class BookingImpl implements BookingDAO {

	@Override
	public List<Booking> getAllBookings() {
		Connection connection = DBConnectionFactory.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM `bookings`");

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

	@Override
	public Booking getBooking(int id) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM `bookings` WHERE `id` = ?");
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

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

	@Override
	public int insertBooking(Booking booking) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO `bookings` VALUES (NULL, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, booking.getEventId());
			preparedStatement.setString(2, booking.getGuestName());
			preparedStatement.setInt(3, booking.getTicketsBooked());
			preparedStatement.setDouble(4, booking.getTicketsValueTotal());

			int hasSaved = preparedStatement.executeUpdate();

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

	@Override
	public boolean updateBooking(Booking booking) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("UPDATE `bookings` SET `id` = ?, `event_id` = ?, `guest_name` = ?, `tickets_booked` = ?, `tickets_value_total` = ?");
			preparedStatement.setInt(1, booking.getId());
			preparedStatement.setInt(2, booking.getEventId());
			preparedStatement.setString(3, booking.getGuestName());
			preparedStatement.setInt(4, booking.getTicketsBooked());
			preparedStatement.setDouble(5, booking.getTicketsValueTotal());

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
	public boolean deleteBooking(Booking booking) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement("DELETE FROM `bookings` WHERE `id` = ?");
			preparedStatement.setInt(1, booking.getId());

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
