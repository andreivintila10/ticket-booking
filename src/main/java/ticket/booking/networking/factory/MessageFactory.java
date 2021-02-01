package ticket.booking.networking.factory;

import ticket.booking.networking.Message;

// MessageFactory interface which defines a makeMessage() constructor.
public interface MessageFactory {
    public Message makeMessage();
}
