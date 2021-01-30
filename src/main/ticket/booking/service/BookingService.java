package main.ticket.booking.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.ticket.booking.DBConnectionFactory;
import main.ticket.booking.dao.BookingDAO;
import main.ticket.booking.dao.BookingImpl;
import main.ticket.booking.dao.EventDAO;
import main.ticket.booking.dao.EventImpl;
import main.ticket.booking.pojo.Booking;
import main.ticket.booking.pojo.Event;

public class BookingService {

	public List<Booking> getBookingsForGuest(String guestName) {
		Connection connection = DBConnectionFactory.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM `bookings` WHERE `guest_name` = ?");
			preparedStatement.setString(1, guestName);
			resultSet = preparedStatement.executeQuery();

			Booking booking;
			List<Booking> bookings = new ArrayList<Booking>();
			while (resultSet.next()) {
				booking = new Booking();

				booking.setId(resultSet.getInt("id"));
				booking.setEventId(resultSet.getInt("event_id"));
				booking.setGuestName(resultSet.getString("guest_name"));
				booking.setTicketsBooked(resultSet.getInt("tickets_booked"));
				booking.setTicketsValueTotal(resultSet.getInt("tickets_value_total"));

				bookings.add(booking);
			}
			
			return bookings;

		} catch(SQLException e) {
			System.out.println("Could not extract bookings for guest " + guestName);
			e.printStackTrace();
		} finally {
			try { resultSet.close(); } catch (Exception e) { /* ignored */ }
			try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
		    try { connection.close(); } catch (Exception e) { /* ignored */ }
		}

		return null;
	}

	public int makeBooking(Booking booking) {
		EventDAO eventDAO = new EventImpl();
		Event event = eventDAO.getEvent(booking.getEventId());

		int ticketsAvailable = event.getTicketsAvailable() - booking.getTicketsBooked();
		if (ticketsAvailable >= 0) {
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement preparedStatementBooking = null;
			PreparedStatement preparedStatementEvent = null;
			ResultSet resultSet = null;

			try {
				connection.setAutoCommit(false);

				preparedStatementBooking = connection.prepareStatement("INSERT INTO `bookings` VALUES (NULL, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				preparedStatementBooking.setInt(1, booking.getEventId());
				preparedStatementBooking.setString(2, booking.getGuestName());
				preparedStatementBooking.setInt(3, booking.getTicketsBooked());
				preparedStatementBooking.setDouble(4, booking.getTicketsValueTotal());
				int hasUpdatedBooking = preparedStatementBooking.executeUpdate();

				preparedStatementEvent = connection.prepareStatement("UPDATE `events` SET `tickets_available` = `tickets_available` - ? WHERE `id` = ? AND `tickets_available` - ? >= 0");
				preparedStatementEvent.setInt(1, booking.getTicketsBooked());
				preparedStatementEvent.setInt(2, booking.getEventId());
				preparedStatementEvent.setInt(3, booking.getTicketsBooked());
				int hasUpdatedEvent = preparedStatementEvent.executeUpdate();

				if (hasUpdatedBooking == 1 && hasUpdatedEvent == 1) {
					connection.commit();

					resultSet = preparedStatementBooking.getGeneratedKeys();
					if (resultSet.next())
						return resultSet.getInt(1);
				}
				else {
					connection.rollback();
				}

			} catch(SQLException e) {
				System.out.println("Could not complete booking for event #" + booking.getEventId() + " attempted by guest " + booking.getGuestName());
				e.printStackTrace();
			} finally {
				try { resultSet.close(); } catch (Exception e) { /* ignored */ }
				try { preparedStatementBooking.close(); } catch (Exception e) { /* ignored */ }
				try { preparedStatementEvent.close(); } catch (Exception e) { /* ignored */ }
			    try { connection.close(); } catch (Exception e) { /* ignored */ }
			}
		}

		return 0;
	}

	public boolean cancelBooking(int bookingId) {
		BookingDAO bookingDAO = new BookingImpl();
		Booking booking = bookingDAO.getBooking(bookingId);

		if (booking != null) {
			Connection connection = DBConnectionFactory.getConnection();
			PreparedStatement preparedStatementBooking = null;
			PreparedStatement preparedStatementEvent = null;

			try {
				connection.setAutoCommit(false);
				preparedStatementBooking = connection.prepareStatement("DELETE FROM `bookings` WHERE `id` = ?");
				preparedStatementBooking.setInt(1, booking.getId());
				int ok1 = preparedStatementBooking.executeUpdate();

				preparedStatementEvent = connection.prepareStatement("UPDATE `events` SET `tickets_available` = `tickets_available` + ? WHERE `id` = ?");
				preparedStatementEvent.setInt(1, booking.getTicketsBooked());
				preparedStatementEvent.setInt(2, booking.getEventId());
				int ok2 = preparedStatementEvent.executeUpdate();

				if (ok1 == 1 && ok2 == 1) {
					connection.commit();
					return true;
				}
				else {
					connection.rollback();
				}

			} catch(SQLException e) {
				try {
					if (connection != null)
						connection.rollback();

				} catch(SQLException ex) {
					ex.printStackTrace();
				}

				System.out.println("Could not cancel booking for event #" + booking.getEventId() + " attempted by guest " + booking.getGuestName());
				e.printStackTrace();

			} finally {
				try { preparedStatementBooking.close(); } catch (Exception e) { /* ignored */ }
				try { preparedStatementEvent.close(); } catch (Exception e) { /* ignored */ }
			    try { connection.close(); } catch (Exception e) { /* ignored */ }
			}
		}

		return false;
	}
}
