package ticket.booking.networking.factory;

import ticket.booking.networking.CancelResponseMsgImpl;
import ticket.booking.networking.Message;
import ticket.booking.service.BookingService;

// Factory class for constructing the cancel response message.
public class CancelResponseMsgFactory implements MessageFactory {
    private int bookingId;

    // Constructor.
    public CancelResponseMsgFactory(int bookingId) {
        this.bookingId = bookingId;
    }

    // Method to make message object.
    @Override
    public Message makeMessage() {
        BookingService bookingService = new BookingService();
        boolean hasCancelled = bookingService.cancelBooking(bookingId);

        int status;
        String message;
        if (hasCancelled) {
            status = 1;
            message = "  Booking successfully cancelled";
        }
        else {
            status = 0;
            message = "  Could not cancel your booking. Please try again";
        }

        return new CancelResponseMsgImpl(status, message);
    }
}
