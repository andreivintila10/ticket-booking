package ticket.booking.networking;

import org.json.JSONObject;

// CancelResponseMsgImpl class for the cancel response message.
public class CancelResponseMsgImpl implements CancelResponseMsg {
    private int status;
    private String message;
    private JSONObject jsonResponse;

    // Empty constructor.
    public CancelResponseMsgImpl() {
        this.status = 0;
        this.message = "";
        this.jsonResponse = new JSONObject();
    }

    // Parametrised Constructor.
    public CancelResponseMsgImpl(int status, String message) {
        this.status = status;
        this.message = message;
        this.jsonResponse = new JSONObject();
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
        message = jsonResponse.getJSONObject("data").getString("message");
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
