package main.ticket.booking.pojo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Event {
	private int id;
	private int movieId;
	private int screenId;
	private Calendar date;
	private double ticketPrice;
	private int ticketsAvailable;

	public Event() {
		this.date = Calendar.getInstance();
	}

	public Event(Movie movie, Screen screen, Calendar date, double ticketPrice) {
		this.movieId = movie.getId();
		this.screenId = screen.getId();
		this.date = date;
		this.ticketPrice = ticketPrice;
		this.ticketsAvailable = screen.getCapacity();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovieId() {
		return this.movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getScreenId() {
		return this.screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public Calendar getDate() {
		return this.date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Timestamp getDateTimestamp() {
		return new Timestamp(this.date.getTimeInMillis());
	}

	public void setDateFromTimestamp(Timestamp timestamp) {
		this.date.setTimeInMillis(timestamp.getTime());
	}

	public long getDateTimeInMillis() {
		return this.date.getTimeInMillis();
	}

	public void setDateFromTimeInMillis(long timeInMillis) {
		this.date.setTimeInMillis(timeInMillis);
	}

	public String getDateFormated() {
		return new SimpleDateFormat("dd MMM, HH:mm").format(this.date.getTime());
	}

	public double getTicketPrice() {
		return this.ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getTicketsAvailable() {
		return this.ticketsAvailable;
	}

	public void setTicketsAvailable(int ticketsAvailable) {
		this.ticketsAvailable = ticketsAvailable;
	}
	
	public String toString() {
		return "Event #" + id;
	}
}
