package ticket.booking.pojo;

public class Screen {
	private int id;
	private int cinemaId;
	private int capacity;

	// Empty constructor.
	public Screen() {
	    /* void */
	}

	// Parametrised constructor overload.
	public Screen(int thisCinemaId, int thisCapacity) {
		cinemaId = thisCinemaId;
		capacity = thisCapacity;
	}

	// Mutator method.
	public void setId(int id) {
		this.id = id;
	}

	// Accessor method.
	public int getId() {
		return id;
	}

	// Accessor method.
	public int getCinemaId() {
		return cinemaId;
	}

	// Mutator method.
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	// Mutator method.
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	// Accessor method.
	public int getCapacity() {
		return capacity;
	}

	// Method toString.
	public String toString() {
		return "Movie Theatre #" + id + " has a capacity of up to " + capacity + " guests";
	}
}