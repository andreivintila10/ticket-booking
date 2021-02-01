package ticket.booking.networking;

// Message interface which defines the basic structure of a Message object.
public interface Message {
    public void createMessage();
    public void parseJSONString(String jsonString);
}
