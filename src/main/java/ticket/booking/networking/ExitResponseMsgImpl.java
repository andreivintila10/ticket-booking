package ticket.booking.networking;

import org.json.JSONObject;

// ExitResponseMsgImpl class for the exit response message.
public class ExitResponseMsgImpl implements ExitResponseMsg {
    private int status;
    private final String message = "  Good bye";
    private JSONObject jsonResponse;

    // Empty constructor.
    public ExitResponseMsgImpl() {
        status = 1;
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
