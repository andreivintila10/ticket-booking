package ticket.booking.dao;

import java.util.List;

import ticket.booking.pojo.Cinema;

// CinemaDAO interface which defines a set of CRUD methods to be implemented.
public interface CinemaDAO {
	public List<Cinema> getAllCinemas();
	public Cinema getCinema(int id);
	public boolean insertCinema(Cinema cinema);
	public boolean updateCinema(Cinema cinema);
	public boolean deleteCinema(Cinema cinema);
}
