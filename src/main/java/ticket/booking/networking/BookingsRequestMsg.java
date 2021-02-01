package ticket.booking.networking;

// BookingsRequestMsg interface which defines an accessor method for guestName.
public interface BookingsRequestMsg extends RequestMessage {
    public String getGuestName();
}
