package main.ticket.booking;

import main.ticket.booking.dao.CinemaDAO;
import main.ticket.booking.dao.CinemaImpl;
import main.ticket.booking.pojo.Cinema;

public class PopulateCinemaDB {
	public static void main(String[] args) {
		CinemaDAO cinemaDAO = new CinemaImpl();
		Cinema cinema = new Cinema("Inspiron", "Mercur Mall");
		cinemaDAO.insertCinema(cinema);
	}
}
