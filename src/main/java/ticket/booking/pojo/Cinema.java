package ticket.booking.pojo;

public class Cinema {
	private int id;
	private String name;
	private String address;

	public Cinema() {}

	public Cinema(String thisName, String thisAddress) {
		this.name = thisName;
		this.address = thisAddress;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String thisName) {
		this.name = thisName;
	}

	public String getName() {
		return this.name;
	}

	public void setAddress(String thisAddress) {
		this.address = thisAddress;
	}

	public String getAddress() {
		return this.address;
	}

	public String toString() {
		return "Welcome to " + this.name + " Cinema!";
	}
}
