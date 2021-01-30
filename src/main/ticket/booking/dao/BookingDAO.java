package main.ticket.booking.dao;

import java.util.List;

import main.ticket.booking.pojo.Booking;

public interface BookingDAO {
	public List<Booking> getAllBookings();
	public Booking getBooking(int id);
	public int insertBooking(Booking booking);
	public boolean updateBooking(Booking booking);
	public boolean deleteBooking(Booking booking);
}
