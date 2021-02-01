package ticket.booking.networking;

import org.json.JSONObject;

// UnknownResponseMsgImpl class for the unknown response message.
public class UnknownResponseMsgImpl implements UnknownResponseMsg {
    private int status;
    private final String message = "  Unknown request";
    private JSONObject jsonResponse;

    // Empty constructor.
    public UnknownResponseMsgImpl() {
        status = 0;
        jsonResponse = new JSONObject();
    }

    // Create the json response message.
    @Override
    public void createMessage() {
        JSONObject responseData = new JSONObject();
        responseData.put("message", message);

        jsonResponse.put("status", status);
        jsonResponse.put("data", responseData);
    }

    // Factory method to construct the message object by parsing the response Json string.
    @Override
    public void parseJSONString(String jsonString) {
        jsonResponse = new JSONObject(jsonString);
        status = jsonResponse.getInt("status");
    }

    // Accessor method implementation for status.
    @Override
    public int getStatus() {
        return status;
    }

    // Accessor method implementation for message.
    @Override
    public String getMessage() {
        return message;
    }

    // Method toString implementation.
    @Override
    public String toString() {
        return jsonResponse.toString();
    }

}
