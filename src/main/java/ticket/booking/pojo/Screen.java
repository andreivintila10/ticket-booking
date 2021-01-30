package ticket.booking.pojo;

public class Screen {
	private int id;
	private int cinemaId;
	private int capacity;

	public Screen() {}

	public Screen(int thisCinemaId, int thisCapacity) {
		cinemaId = thisCinemaId;
		capacity = thisCapacity;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public String toString() {
		return "Movie Theatre #" + id + " has a capacity of up to " + capacity + " guests";
	}
}