package ticket.booking.networking;

import org.json.JSONObject;

// EventsRequestMsgImpl class for the show events request message.
public class EventsRequestMsgImpl implements EventsRequestMsg {
    private final String type = "show_events";
    private JSONObject jsonRequest;

    // Empty constructor.
    public EventsRequestMsgImpl() {
        this.jsonRequest = new JSONObject();
    }

    // Create the json request message.
    @Override
    public void createMessage() {
        JSONObject jsonParams = new JSONObject();

        jsonRequest.put("type", type);
        jsonRequest.put("params", jsonParams);
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
