package ticket.booking.pojo;

public class Booking {
	private int id;
	private int eventId;
	private String guestName;
	private int ticketsBooked;
	private double ticketsValueTotal;

	public Booking() {}

	public Booking(int eventId, String guestName, int ticketsBooked, double ticketsValueTotal) {
		this.eventId = eventId;
		this.guestName = guestName;
		this.ticketsBooked = ticketsBooked;
		this.ticketsValueTotal = ticketsValueTotal;
	}
	
	public Booking(int id, int eventId, String guestName, int ticketsBooked, double ticketsValueTotal) {
		this.id = id;
		this.eventId = eventId;
		this.guestName = guestName;
		this.ticketsBooked = ticketsBooked;
		this.ticketsValueTotal = ticketsValueTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public int getTicketsBooked() {
		return ticketsBooked;
	}

	public void setTicketsBooked(int ticketsBooked) {
		this.ticketsBooked = ticketsBooked;
	}

	public double getTicketsValueTotal() {
		return ticketsValueTotal;
	}

	public void setTicketsValueTotal(double ticketsValueTotal) {
		this.ticketsValueTotal = ticketsValueTotal;
	}
}
