package ticket.booking.networking;

import java.util.List;

import ticket.booking.pojo.Event;

// EventsResponseMsg interface which defines two accessor methods for events and movieTitles.
public interface EventsResponseMsg extends ResponseMessage {
    public List<Event> getEvents();
    public List<String> getMovieTitles();
}
