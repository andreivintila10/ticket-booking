package ticket.booking.networking;

// ResponseMessage interface which defines a standard 'status' accessor method for response messages.
public interface ResponseMessage extends Message {
    public int getStatus();
}
