package ticket.booking.pojo;

// Cinema class.
public class Cinema {
	private int id;
	private String name;
	private String address;

	// Empty constructor.
	public Cinema() {
	    /* void */
	}

	// Parametrised constructor overload.
	public Cinema(String thisName, String thisAddress) {
		this.name = thisName;
		this.address = thisAddress;
	}

	// Accessor method.
	public int getId() {
		return this.id;
	}

	// Mutator method.
	public void setId(int id) {
		this.id = id;
	}

	// Mutator method.
	public void setName(String thisName) {
		this.name = thisName;
	}

	// Accessor method.
	public String getName() {
		return this.name;
	}

	// Mutator method.
	public void setAddress(String thisAddress) {
		this.address = thisAddress;
	}

	// Accessor method.
	public String getAddress() {
		return this.address;
	}

	// Method toString.
	public String toString() {
		return "Welcome to " + this.name + " Cinema!";
	}
}
