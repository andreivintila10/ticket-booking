package ticket.booking.networking;

// BookRequestMsg interface which defines three accessor methods for eventId, ticketsBooked and guestName.
public interface BookRequestMsg extends RequestMessage {
    public int getEventId();
    public int getTicketsBooked();
    public String getGuestName();
}
