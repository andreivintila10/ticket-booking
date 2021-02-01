package ticket.booking.pojo;

// Booking class.
public class Booking {
	private int id;
	private int eventId;
	private String guestName;
	private int ticketsBooked;
	private double ticketsValueTotal;

	// Empty constructor.
	public Booking() {
	    /* void */
	}

	// Parametrised constructor overload 1.
	public Booking(int eventId, String guestName, int ticketsBooked, double ticketsValueTotal) {
		this.eventId = eventId;
		this.guestName = guestName;
		this.ticketsBooked = ticketsBooked;
		this.ticketsValueTotal = ticketsValueTotal;
	}

	// Parametrised constructor overload 2.
	public Booking(int id, int eventId, String guestName, int ticketsBooked, double ticketsValueTotal) {
		this.id = id;
		this.eventId = eventId;
		this.guestName = guestName;
		this.ticketsBooked = ticketsBooked;
		this.ticketsValueTotal = ticketsValueTotal;
	}

	// Accessor method.
	public int getId() {
		return id;
	}

	// Mutator method.
	public void setId(int id) {
		this.id = id;
	}

	// Accessor method.
	public int getEventId() {
		return eventId;
	}

	// Mutator method.
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	// Accessor method.
	public String getGuestName() {
		return guestName;
	}

	// Mutator method.
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	// Accessor method.
	public int getTicketsBooked() {
		return ticketsBooked;
	}

	// Mutator method.
	public void setTicketsBooked(int ticketsBooked) {
		this.ticketsBooked = ticketsBooked;
	}

	// Accessor method.
	public double getTicketsValueTotal() {
		return ticketsValueTotal;
	}

	// Mutator method.
	public void setTicketsValueTotal(double ticketsValueTotal) {
		this.ticketsValueTotal = ticketsValueTotal;
	}

}
