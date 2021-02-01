package ticket.booking.dao;

import java.util.List;

import ticket.booking.pojo.Booking;

// BookingDAO interface which defines a set of CRUD methods to be implemented.
public interface BookingDAO {
	public List<Booking> getAllBookings();
	public Booking getBooking(int id);
	public int insertBooking(Booking booking);
	public boolean updateBooking(Booking booking);
	public boolean deleteBooking(Booking booking);
}
