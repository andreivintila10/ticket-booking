package ticket.booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import ticket.booking.pojo.Booking;
import ticket.booking.pojo.Event;


public class TicketBooking {

	private static void printMenu() {
		System.out.println("=========MENU=========");
		System.out.println("1. Show My Bookings");
		System.out.println("2. Make Booking");
		System.out.println("3. Cancel Booking");
		System.out.println();
		System.out.println("0. Exit");
		System.out.println("======================");
	}

	private static void printBookings(List<Booking> bookings) {
		if (bookings != null) {
			System.out.println("  Showing bookings");
			System.out.println();
			System.out.format("  %10s | %8s | %14s | %19s%n", "Booking id", "Event id", "Tickets Booked", "Tickets Total Value");
			System.out.println("  -----------|----------|----------------|--------------------");
			for (Booking booking : bookings) {
				System.out.format("        %04d |     %04d |            %3d |            %8.2f%n", booking.getId(), booking.getEventId(), booking.getTicketsBooked(), booking.getTicketsValueTotal());
			}
			System.out.println("  -----------|----------|----------------|--------------------");
		}
	}

	private static void printEvents(List<Event> events, List<String> movieTitles) {
		if (events != null) {
			System.out.println("  Showing events");
			System.out.println();
			System.out.format("  Event id | %40s | Ticket Price | %13s | Tickets Available%n", "Movie", "Date & Time");
			System.out.println("  ---------|------------------------------------------|--------------|---------------|------------------");
			Event event;
			String movieTitle;
			for (int index = 0; index < events.size(); index++) {
				event = events.get(index);
				movieTitle = movieTitles.get(index);
				System.out.format("      %04d | %40s |     %8.2f | %13s |               %3d%n", event.getId(), movieTitle, event.getTicketPrice(), event.getDateFormated(), event.getTicketsAvailable());
			}
			System.out.println("  ---------|------------------------------------------|--------------|---------------|------------------");
		}
	}

	public static void run(Client client) throws IOException {
		printMenu();

		String message, response, guestName;
		String[] split_response;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int option;
		do {
			System.out.print("Option: ");
			option = Integer.parseInt(in.readLine());
			switch (option) {
				case 1:	 System.out.print("  Your name: ");
						 guestName = in.readLine();
						 message = "send_bookings," + guestName;
						 response = client.sendMessage(message);
						 split_response = response.split(":");
						 if (Boolean.parseBoolean(split_response[0])) {
							 List<Booking> bookings = new ArrayList<Booking>();

							 String[] stringBookings = split_response[1].split(";");
							 for (String stringBooking : stringBookings) {
								 String[] bookingAttr = stringBooking.split(",");
								 Booking booking = new Booking(Integer.parseInt(bookingAttr[0]), Integer.parseInt(bookingAttr[1]), bookingAttr[2], 
										 					   Integer.parseInt(bookingAttr[3]), Double.parseDouble(bookingAttr[4]));
								 bookings.add(booking);
							 }
							 printBookings(bookings);
						 }
						 else {
							 System.out.println(split_response[1]);
						 }

						 System.out.println();
						 break;

				case 2:  response = client.sendMessage("send_events,true");
						 split_response = response.split(":");
						 if (Boolean.parseBoolean(split_response[0])) {
							 List<Event> events = new ArrayList<Event>();
							 List<String> movieTitles = new ArrayList<String>();

							 String[] stringEvents = split_response[1].split(";");
							 for (String stringEvent : stringEvents) {
								 String[] eventAttr = stringEvent.split(",");
								 Event event = new Event();

								 event.setId(Integer.parseInt(eventAttr[0]));
								 movieTitles.add(eventAttr[1]);
								 event.setScreenId(Integer.parseInt(eventAttr[2]));
								 event.setDateFromTimeInMillis(Long.parseLong(eventAttr[3]));
								 event.setTicketPrice(Double.parseDouble(eventAttr[4]));
								 event.setTicketsAvailable(Integer.parseInt(eventAttr[5]));
								 
								 events.add(event);
							 }
							 printEvents(events, movieTitles);
						 }
						 else {
							 System.out.println(split_response[1]);
							 System.out.println();
							 break;
						 }

						 System.out.println();
						 System.out.print("  Book event: ");
						 int eventId = Integer.parseInt(in.readLine());
						 System.out.print("  Number of tickets: ");
						 int ticketsNo = Integer.parseInt(in.readLine());
						 System.out.print("  Guest name: ");
						 guestName = in.readLine();
						 message = "book," + eventId + "," + guestName + "," + ticketsNo;
						 response = client.sendMessage(message);
						 System.out.println(response);
						 System.out.println();
						 break;

				case 3:  System.out.print("  Booking id: ");
						 int bookingId = Integer.parseInt(in.readLine());
						 System.out.print("  Are you sure you want to cancel your booking? (Y/N): ");
						 String yesNo = in.readLine();
						 if (yesNo.toLowerCase().equals("yes") || yesNo.toLowerCase().equals("y")) {
							 message = "cancel," + bookingId;
							 response = client.sendMessage(message);
							 System.out.println(response);
						 }
						 else {
							 System.out.println("  Cancellation aborted");
						 }

						 System.out.println();
						 break;

				case 0:  System.out.println("  Client exiting...");
						 client.sendMessage("bye");
						 in.close();
						 break;

				default: System.out.println("  Invalid option");
						 System.out.println();
						 break;
			}

		} while (option != 0);
	}


	public static void main(String[] args) throws IOException {
		Client client = new Client();
		client.startConnection("127.0.0.1", 4444);
		run(client);
		client.stopConnection();
	}
}