package main.ticket.booking.dao;

import java.util.List;

import main.ticket.booking.pojo.Cinema;

public interface CinemaDAO {
	public List<Cinema> getAllCinemas();
	public Cinema getCinema(int id);
	public boolean insertCinema(Cinema cinema);
	public boolean updateCinema(Cinema cinema);
	public boolean deleteCinema(Cinema cinema);
}
