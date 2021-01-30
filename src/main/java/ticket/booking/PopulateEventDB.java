package ticket.booking;

import java.util.Calendar;

import ticket.booking.dao.MovieDAO;
import ticket.booking.dao.MovieImpl;
import ticket.booking.dao.EventDAO;
import ticket.booking.dao.EventImpl;
import ticket.booking.dao.ScreenDAO;
import ticket.booking.dao.ScreenImpl;
import ticket.booking.pojo.Movie;
import ticket.booking.pojo.Event;
import ticket.booking.pojo.Screen;

public class PopulateEventDB {
	public static void main(String[] args) {
		EventDAO eventDAO = new EventImpl();
		MovieDAO movieDAO = new MovieImpl();
		ScreenDAO screenDAO = new ScreenImpl();

		Screen screen = screenDAO.getScreen(1);
		Movie movie = movieDAO.getMovieByTitle("The Terminator");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 18);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Event event = new Event(movie, screen, calendar, 25.00);
		eventDAO.insertEvent(event);

		screen = screenDAO.getScreen(2);
		movie = movieDAO.getMovieByTitle("Law Abiding Citizen");
		calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 19);
		calendar.set(Calendar.MINUTE, 0);
		event = new Event(movie, screen, calendar, 25.00);
		eventDAO.insertEvent(event);

		screen = screenDAO.getScreen(3);
		movie = movieDAO.getMovieByTitle("The Avengers");
		calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 20);
		calendar.set(Calendar.MINUTE, 0);
		event = new Event(movie, screen, calendar, 30.00);
		eventDAO.insertEvent(event);

		screen = screenDAO.getScreen(4);
		movie = movieDAO.getMovieByTitle("The Avengers");
		calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 21);
		calendar.set(Calendar.MINUTE, 0);
		event = new Event(movie, screen, calendar, 30.00);
		eventDAO.insertEvent(event);

		screen = screenDAO.getScreen(5);
		movie = movieDAO.getMovieByTitle("Ouija - Origin of Evil");
		calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 22);
		calendar.set(Calendar.MINUTE, 0);
		event = new Event(movie, screen, calendar, 20.00);
		eventDAO.insertEvent(event);

		screen = screenDAO.getScreen(1);
		movie = movieDAO.getMovieByTitle("Fight Club");
		calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 22);
		calendar.set(Calendar.MINUTE, 0);
		event = new Event(movie, screen, calendar, 20.00);
		eventDAO.insertEvent(event);
	}
}
