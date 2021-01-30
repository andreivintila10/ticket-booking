package ticket.booking.dao;

import java.util.List;

import ticket.booking.pojo.Event;

public interface EventDAO {
	public List<Event> getAllEvents();
	public Event getEvent(int id);
	public int insertEvent(Event event);
	public boolean updateEvent(Event event);
	public boolean deleteEvent(Event event);
}
