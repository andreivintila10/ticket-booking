package ticket.booking.networking;

// RequestMessage interface which defines a standard 'type' accessor method for request messages.
public interface RequestMessage extends Message {
    public String getType();
}
