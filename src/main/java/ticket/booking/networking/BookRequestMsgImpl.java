package ticket.booking.networking;

import org.json.JSONObject;

// BookRequestMsgImpl class for the book request message.
public class BookRequestMsgImpl implements BookRequestMsg {
    private final String type = "book";
    private int eventId;
    private int ticketsBooked;
    private String guestName;
    private JSONObject jsonRequest;

    // Empty constructor.
    public BookRequestMsgImpl() {
        this.eventId = 0;
        this.ticketsBooked = 0;
        this.guestName = "";
        jsonRequest = new JSONObject();
    }

    // Parametrised Constructor.
    public BookRequestMsgImpl(int eventId, int ticketsBooked, String guestName) {
        this.eventId = eventId;
        this.ticketsBooked = ticketsBooked;
        this.guestName = guestName;
        jsonRequest = new JSONObject();
    }

    // Create the json request message.
    @Override
    public void createMessage() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("event_id", eventId);
        requestParams.put("tickets_booked", ticketsBooked);
        requestParams.put("guest_name", guestName);

        jsonRequest.put("type", type);
        jsonRequest.put("params", requestParams);
    }

    // Factory method to construct the message object by parsing the request Json string.
    @Override
    public void parseJSONString(String jsonString) {
        jsonRequest = new JSONObject(jsonString);
        JSONObject requestParams = jsonRequest.getJSONObject("params");

        eventId = requestParams.getInt("event_id");
        ticketsBooked = requestParams.getInt("tickets_booked");
        guestName = requestParams.getString("guest_name");
    }

    // Accessor method implementation for type.
    @Override
    public String getType() {
        return type;
    }

    // Accessor method implementation for eventId.
    @Override
    public int getEventId() {
        return eventId;
    }

    // Accessor method implementation for ticketsBooked.
    @Override
    public int getTicketsBooked() {
        return ticketsBooked;
    }

    // Accessor method implementation for guestName.
    @Override
    public String getGuestName() {
        return guestName;
    }

    // Method toString implementation.
    @Override
    public String toString() {
        return jsonRequest.toString();
    }

}
