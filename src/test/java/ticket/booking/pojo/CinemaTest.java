package ticket.booking.pojo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CinemaTest {
	@Test
	void testConstructor() {
		Cinema cinema = new Cinema();
		assertEquals(cinema.getClass(), Cinema.class);
		assertEquals(cinema.getName(), null);
		assertEquals(cinema.getAddress(), null);

		String name = "Inspiron";
		String address = "Mercur Mall";
		Cinema inspiron = new Cinema(name, address);
		assertEquals(inspiron.getClass(), Cinema.class);
		assertEquals(inspiron.getName(), name);
		assertEquals(inspiron.getAddress(), address);
	}

	@Test
	void testSetAndGetName() {
		Cinema cinema = new Cinema();
		String name = "Inspiron";
		cinema.setName(name);
		assertEquals(cinema.getName(), name);
	}
	
	@Test
	void testSetAndGetAddress() {
		Cinema cinema = new Cinema();
		String address = "Mercur Mall";
		cinema.setAddress(address);
		assertEquals(cinema.getAddress(), address);
	}
	
	@Test
	void testToString() {
		Cinema cinema = new Cinema("Vue", "Printworks");
		assertEquals(cinema.toString(), "Welcome to Vue Cinema!");
	}
}