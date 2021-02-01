package ticket.booking.pojo;

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

	// Empty constructor.
	public Event() {
		this.date = Calendar.getInstance();
	}

	// Parametrised constructor overload.
	public Event(Movie movie, Screen screen, Calendar date, double ticketPrice) {
		this.movieId = movie.getId();
		this.screenId = screen.getId();
		this.date = date;
		this.ticketPrice = ticketPrice;
		this.ticketsAvailable = screen.getCapacity();
	}

	// Accessor method.
	public int getId() {
		return this.id;
	}

	// Mutator method.
	public void setId(int id) {
		this.id = id;
	}

	// Accessor method.
	public int getMovieId() {
		return this.movieId;
	}

	// Mutator method.
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	// Accessor method.
	public int getScreenId() {
		return this.screenId;
	}

	// Mutator method.
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	// Accessor method.
	public Calendar getDate() {
		return this.date;
	}

	// Mutator method.
	public void setDate(Calendar date) {
		this.date = date;
	}

	// Accessor method.
	public Timestamp getDateTimestamp() {
		return new Timestamp(this.date.getTimeInMillis());
	}

	// Mutator method.
	public void setDateFromTimestamp(Timestamp timestamp) {
		this.date.setTimeInMillis(timestamp.getTime());
	}

	// Accessor method.
	public long getDateTimeInMillis() {
		return this.date.getTimeInMillis();
	}

	// Mutator method.
	public void setDateFromTimeInMillis(long timeInMillis) {
		this.date.setTimeInMillis(timeInMillis);
	}

	// Accessor method.
	public String getDateFormated() {
		return new SimpleDateFormat("dd MMM, HH:mm").format(this.date.getTime());
	}

	// Accessor method.
	public double getTicketPrice() {
		return this.ticketPrice;
	}

	// Mutator method.
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	// Accessor method.
	public int getTicketsAvailable() {
		return this.ticketsAvailable;
	}

	// Mutator method.
	public void setTicketsAvailable(int ticketsAvailable) {
		this.ticketsAvailable = ticketsAvailable;
	}

	// Method toString.
	public String toString() {
		return "Event #" + id;
	}
}
