package ticket.booking.networking;

import java.util.List;

import ticket.booking.pojo.Booking;

// BookingsResponseMsg interface which defines an accessor method for bookings.
public interface BookingsResponseMsg extends ResponseMessage {
    public List<Booking> getBookings();
}
