package ticket.booking.networking.factory;

import java.util.List;

import ticket.booking.networking.BookingsResponseMsgImpl;
import ticket.booking.networking.Message;
import ticket.booking.pojo.Booking;
import ticket.booking.service.BookingService;

// Factory class for constructing the show bookings response message.
public class BookingsResponseMsgFactory implements MessageFactory {
    private String guestName;

    // Constructor.
    public BookingsResponseMsgFactory(String guestName) {
        this.guestName = guestName;
    }

    // Method to make message object.
    @Override
    public Message makeMessage() {
        BookingService bookingService = new BookingService();
        List<Booking> bookings = bookingService.getBookingsForGuest(guestName);

        return new BookingsResponseMsgImpl(bookings);
    }

}
