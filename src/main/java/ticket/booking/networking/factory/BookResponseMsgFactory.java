package ticket.booking.networking.factory;

import ticket.booking.dao.EventDAO;
import ticket.booking.dao.EventImpl;
import ticket.booking.networking.BookResponseMsgImpl;
import ticket.booking.networking.Message;
import ticket.booking.pojo.Booking;
import ticket.booking.pojo.Event;
import ticket.booking.service.BookingService;

// Factory class for constructing the book response message.
public class BookResponseMsgFactory implements MessageFactory {
    private int eventId;
    private int ticketsBooked;
    private String guestName;

    // Constructor.
    public BookResponseMsgFactory(int eventId, int ticketsBooked, String guestName) {
        this.eventId = eventId;
        this.ticketsBooked = ticketsBooked;
        this.guestName = guestName;
    }

    // Method to make message object.
    @Override
    public Message makeMessage() {
        BookingService bookingService = new BookingService();
        EventDAO eventDAO = new EventImpl();
        Event thisEvent = eventDAO.getEvent(eventId);

        int status;
        int bookingReference;
        String message;
        if (thisEvent == null) {
            status = 0;
            message = "  Selected event does not exist";
        }
        else {
            Booking thisBooking = new Booking(eventId, guestName, ticketsBooked, ticketsBooked * thisEvent.getTicketPrice());
            bookingReference = bookingService.makeBooking(thisBooking);
            if (bookingReference == 0) {
                status = 0;
                message = "  Could not register your booking. Please try again";
            }
            else {
                status = 1;
                message = "  Booking reference id: " + bookingReference;
            }
        }

        return new BookResponseMsgImpl(status, message);
    }

}
