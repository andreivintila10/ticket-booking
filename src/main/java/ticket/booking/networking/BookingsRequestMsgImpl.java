package ticket.booking.networking;

import org.json.JSONObject;

// BookingsRequestMsgImpl class for the show bookings request message.
public class BookingsRequestMsgImpl implements BookingsRequestMsg {
    private final String type = "show_bookings";
    private String guestName;
    private JSONObject jsonRequest;

    // Empty constructor.
    public BookingsRequestMsgImpl() {
        this.guestName = "";
        this.jsonRequest = new JSONObject();
    }

    // Parametrised Constructor.
    public BookingsRequestMsgImpl(String guestName) {
        this.guestName = guestName;
        this.jsonRequest = new JSONObject();
    }

    // Create the json request message.
    @Override
    public void createMessage() {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("guest_name", guestName);

        jsonRequest.put("type", type);
        jsonRequest.put("params", jsonParams);
    }

    // Factory method to construct the message object by parsing the request Json string.
    @Override
    public void parseJSONString(String jsonString) {
        jsonRequest = new JSONObject(jsonString);
        guestName = jsonRequest.getJSONObject("params").getString("guest_name");
    }

    // Accessor method implementation for type.
    @Override
    public String getType() {
        return type;
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
