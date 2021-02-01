package ticket.booking.networking;

import org.json.JSONObject;

// CancelRequestMsgImpl class for the cancel request message.
public class CancelRequestMsgImpl implements CancelRequestMsg {
    private final String type = "cancel";
    private int bookingId;
    private JSONObject jsonRequest;

    // Empty constructor.
    public CancelRequestMsgImpl() {
        this.bookingId = 0;
        this.jsonRequest = new JSONObject();
    }

    // Parametrised Constructor.
    public CancelRequestMsgImpl(int bookingId) {
        this.bookingId = bookingId;
        this.jsonRequest = new JSONObject();
    }

    // Create the json request message.
    @Override
    public void createMessage() {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("booking_id", bookingId);

        jsonRequest.put("type", type);
        jsonRequest.put("params", jsonParams);
    }

    // Factory method to construct the message object by parsing the request Json string.
    @Override
    public void parseJSONString(String jsonString) {
        jsonRequest = new JSONObject(jsonString);
        bookingId = jsonRequest.getJSONObject("params").getInt("booking_id");
    }

    // Accessor method implementation for type.
    @Override
    public String getType() {
        return type;
    }

    // Accessor method implementation for bookingId.
    @Override
    public int getBookingId() {
        return bookingId;
    }

    // Method toString implementation.
    @Override
    public String toString() {
        return jsonRequest.toString();
    }

}
