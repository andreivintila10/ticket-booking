package ticket.booking.networking;

// CancelRequestMsg interface which defines an accessor method for bookingId.
public interface CancelRequestMsg extends RequestMessage {
    public int getBookingId();
}
