package ticket.booking.networking;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import ticket.booking.pojo.Event;

// EventsResponseMsgImpl class for the show events response message.
public class EventsResponseMsgImpl implements EventsResponseMsg {
    private int status;
    private List<Event> events;
    private List<String> movieTitles;
    private JSONObject jsonResponse;

    // Empty constructor.
    public EventsResponseMsgImpl() {
        this.status = 0;
        this.events = new ArrayList<Event>();
        this.jsonResponse = new JSONObject();
    }

    // Parametrised Constructor.
    public EventsResponseMsgImpl(List<Event> events, List<String> movieTitles) {
        this.status = events.size() > 0 ? 1 : 0;
        this.events = events;
        this.movieTitles = movieTitles;
        this.jsonResponse = new JSONObject();
    }

    // Create the json response message.
    @Override
    public void createMessage() {
        JSONObject responseData = new JSONObject();

        JSONArray jsonEvents = new JSONArray();
        JSONObject jsonEvent = null;

        int index = 0;
        for (Event event : events) {
            jsonEvent = new JSONObject();

            jsonEvent.put("event_id", event.getId());
            jsonEvent.put("movie_title", movieTitles.get(index));
            jsonEvent.put("screen_id", event.getScreenId());
            jsonEvent.put("date", event.getDateTimeInMillis());
            jsonEvent.put("ticket_price", event.getTicketPrice());
            jsonEvent.put("tickets_available", event.getTicketsAvailable());

            jsonEvents.put(jsonEvent);
            index++;
        }

        responseData.put("events", jsonEvents);
        jsonResponse.put("data", responseData);
        jsonResponse.put("status", status);
    }

    // Factory method to construct the message object by parsing the response Json string.
    @Override
    public void parseJSONString(String jsonString) {
        jsonResponse = new JSONObject(jsonString);
        status = jsonResponse.getInt("status");

        JSONObject responseData = jsonResponse.getJSONObject("data");

        JSONObject jsonEvent = null;
        JSONArray jsonEvents = responseData.getJSONArray("events");

        Event theEvent;
        List<Event> theEvents = new ArrayList<Event>();
        List<String> theMovieTitles = new ArrayList<String>();
        for (int index = 0; index < jsonEvents.length(); index++) {
            jsonEvent = jsonEvents.getJSONObject(index);
            theEvent = new Event();

            theEvent.setId(jsonEvent.getInt("event_id"));
            theEvent.setScreenId(jsonEvent.getInt("screen_id"));
            theEvent.setDateFromTimeInMillis(jsonEvent.getLong("date"));
            theEvent.setTicketPrice(jsonEvent.getDouble("ticket_price"));
            theEvent.setTicketsAvailable(jsonEvent.getInt("tickets_available"));

            theEvents.add(theEvent);
            theMovieTitles.add(jsonEvent.getString("movie_title"));
        }

        events = theEvents;
        movieTitles = theMovieTitles;
    }

    // Accessor method implementation for status.
    @Override
    public int getStatus() {
        return status;
    }

    // Accessor method implementation for events.
    @Override
    public List<Event> getEvents() {
        return events;
    }

    // Accessor method implementation for movieTitles.
    @Override
    public List<String> getMovieTitles() {
        return movieTitles;
    }

    // Method toString implementation.
    @Override
    public String toString() {
        return jsonResponse.toString();
    }

}
