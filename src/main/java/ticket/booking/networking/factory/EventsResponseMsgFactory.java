package ticket.booking.networking.factory;

import java.util.ArrayList;
import java.util.List;

import ticket.booking.networking.EventsResponseMsgImpl;
import ticket.booking.networking.Message;

import ticket.booking.dao.EventDAO;
import ticket.booking.dao.EventImpl;
import ticket.booking.dao.MovieDAO;
import ticket.booking.dao.MovieImpl;

import ticket.booking.pojo.Event;
import ticket.booking.pojo.Movie;

// Factory class for constructing the show events response message.
public class EventsResponseMsgFactory implements MessageFactory {

    // Constructor.
    public EventsResponseMsgFactory() {
        /* void */
    }

    // Method to make message object.
    @Override
    public Message makeMessage() {
        EventDAO eventDAO = new EventImpl();
        MovieDAO movieDAO = new MovieImpl();
        List<Event> events = eventDAO.getAllEvents();
        List<String> movieTitles = new ArrayList<String>();
        Movie thisMovie;
        for (Event event : events) {
            thisMovie = movieDAO.getMovie(event.getMovieId());
            movieTitles.add(thisMovie.getTitle());
        }

        return new EventsResponseMsgImpl(events, movieTitles);
    }

}