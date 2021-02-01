package ticket.booking.networking;

import org.json.JSONObject;

// ExitRequestMsgImpl class for the exit request message.
public class ExitRequestMsgImpl implements ExitRequestMsg {
    private final String type = "bye";
    private JSONObject jsonRequest;

    // Empty constructor.
    public ExitRequestMsgImpl() {
        this.jsonRequest = new JSONObject();
    }

    // Create the json request message.
    @Override
    public void createMessage() {
        JSONObject requestParams = new JSONObject();

        jsonRequest.put("type", type);
        jsonRequest.put("params", requestParams);
    }

    // Factory method to construct the message object by parsing the request Json string.
    @Override
    public void parseJSONString(String jsonString) {
        jsonRequest = new JSONObject(jsonString);
    }

    // Accessor method implementation for type.
    @Override
    public String getType() {
        return type;
    }

    // Method toString implementation.
    @Override
    public String toString() {
        return jsonRequest.toString();
    }

}
