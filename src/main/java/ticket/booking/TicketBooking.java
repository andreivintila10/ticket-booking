package ticket.booking;

import java.io.*;
import java.util.List;

import ticket.booking.networking.BookRequestMsgImpl;
import ticket.booking.networking.BookResponseMsg;
import ticket.booking.networking.BookResponseMsgImpl;
import ticket.booking.networking.BookingsRequestMsgImpl;
import ticket.booking.networking.BookingsResponseMsg;
import ticket.booking.networking.BookingsResponseMsgImpl;
import ticket.booking.networking.CancelRequestMsgImpl;
import ticket.booking.networking.CancelResponseMsg;
import ticket.booking.networking.CancelResponseMsgImpl;
import ticket.booking.networking.EventsRequestMsgImpl;
import ticket.booking.networking.EventsResponseMsg;
import ticket.booking.networking.EventsResponseMsgImpl;
import ticket.booking.networking.ExitRequestMsgImpl;
import ticket.booking.networking.ExitResponseMsg;
import ticket.booking.networking.ExitResponseMsgImpl;
import ticket.booking.networking.Message;

import ticket.booking.pojo.Booking;
import ticket.booking.pojo.Event;


// TicketBooking class.
public class TicketBooking {

    // Method to print the menu with option.
	private static void printMenu() {
		System.out.println("=========MENU=========");
		System.out.println("1. Show My Bookings");
		System.out.println("2. Make Booking");
		System.out.println("3. Cancel Booking");
		System.out.println();
		System.out.println("0. Exit");
		System.out.println("======================");
	}

	// Method to print a user friendly table of bookings.
	private static void printBookings(List<Booking> bookings) {
		System.out.println("  Showing bookings");
		System.out.println();
		System.out.format("  %10s | %8s | %14s | %19s%n", "Booking id", "Event id", "Tickets Booked", "Tickets Total Value");
		System.out.println("  -----------|----------|----------------|--------------------");
		for (Booking booking : bookings) {
			System.out.format("        %04d |     %04d |            %3d |            %8.2f%n", booking.getId(), booking.getEventId(), booking.getTicketsBooked(), booking.getTicketsValueTotal());
		}
		System.out.println("  -----------|----------|----------------|--------------------");
	}

	// Method to print a user friendly table of events.
	private static void printEvents(List<Event> events, List<String> movieTitles) {
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

	public static boolean validateYesNo(String string) {
	    return string.toLowerCase().equals("yes") || string.toLowerCase().equals("y");
	}

	// Method to process client-server communication.
	public static void run(Client client) throws IOException {
		printMenu();
		System.out.println();

		// Open standard input stream reader.
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// Declaring some variables that will be reused among each request.
		int eventId, ticketsBooked, bookingId;
		String response, guestName;
		int option;
		do {
		    // Get option.
		    System.out.print("Option: ");
			option = Integer.parseInt(in.readLine());

			// A message exchange happens by sending a request message to the server and then fetching
            // the response once it is ready. The request message is created so it doesn't require
            // the need to access any parameters and hence, we can use the base interface Message to define
            // our request message.
			// Identify chosen option.
			switch (option) {
			    // Option to display bookings made on a given guestName.
				case 1:	 System.out.print("  Your name: ");
						 guestName = in.readLine();

						 Message bookingsRequest = new BookingsRequestMsgImpl(guestName);
						 bookingsRequest.createMessage();
						 response = client.sendMessage(bookingsRequest);

						 BookingsResponseMsg bookingsResponse = new BookingsResponseMsgImpl();
						 bookingsResponse.parseJSONString(response);

						 if (bookingsResponse.getStatus() == 1) {
							 printBookings(bookingsResponse.getBookings());
						 }
						 else {
							 System.out.println("  No bookings were made by guest " + guestName);
						 }

						 System.out.println();
						 break;

				// Option to make a booking for a user.
			    // Two client-server message exchanges happen here.
				// The first one asks for a list of all current Events and then displays it to the user.
				// The second one asks to make a booking for the desired event and then displays
			    // a booking reference number upon a successful booking.
				case 2:  Message eventsRequest = new EventsRequestMsgImpl();
                         eventsRequest.createMessage();
                         response = client.sendMessage(eventsRequest);

                         EventsResponseMsg eventsResponse = new EventsResponseMsgImpl();
                         eventsResponse.parseJSONString(response);

                         if (eventsResponse.getStatus() == 1) {
                             printEvents(eventsResponse.getEvents(), eventsResponse.getMovieTitles());
                             System.out.println();

                             System.out.print("  Book event: ");
                             eventId = Integer.parseInt(in.readLine());

                             System.out.print("  Number of tickets: ");
                             ticketsBooked = Integer.parseInt(in.readLine());

                             System.out.print("  Guest name: ");
                             guestName = in.readLine();

                             // Make booking and wait for a booking reference number if successful.
                             Message bookRequest = new BookRequestMsgImpl(eventId, ticketsBooked, guestName);
                             bookRequest.createMessage();
                             response = client.sendMessage(bookRequest);

                             BookResponseMsg bookResponse = new BookResponseMsgImpl();
                             bookResponse.parseJSONString(response);

                             System.out.println(bookResponse.getMessage());
                         }
                         else {
                             System.out.println("  There are no events at the moment");
                         }

						 System.out.println();
						 break;

				// Option to cancel a booking.
				case 3:  System.out.print("  Booking id: ");
                         bookingId = Integer.parseInt(in.readLine());

                         System.out.print("  Are you sure you want to cancel your booking? (Y/N): ");
                         String yesNo = in.readLine();

                         if (TicketBooking.validateYesNo(yesNo)) {
                             Message cancelRequest = new CancelRequestMsgImpl(bookingId);
                             cancelRequest.createMessage();
                             response = client.sendMessage(cancelRequest);

                             CancelResponseMsg cancelResponse = new CancelResponseMsgImpl();
                             cancelResponse.parseJSONString(response);

                             System.out.println(cancelResponse.getMessage());
                         }
                         else {
                             System.out.println("  Cancellation revoked");
                         }

						 System.out.println();
						 break;

				// Option to exit client.
				case 0:  Message exitRequest = new ExitRequestMsgImpl();
				         exitRequest.createMessage();
                         response = client.sendMessage(exitRequest);

                         ExitResponseMsg exitResponse = new ExitResponseMsgImpl();
                         exitResponse.parseJSONString(response);
                         System.out.println(exitResponse.getMessage());
                         System.out.println();
						 break;

				// Default option in case the selected option is not defined.
				default: System.out.println("  Invalid option");
						 System.out.println();
						 break;
			}

		} while (option != 0);    // Keep asking for options until the user wishes to exit.

		System.out.println("Client exiting...");
		in.close();
	}


	public static void main(String[] args) throws IOException {
		Client client = new Client();
		client.startConnection("127.0.0.1", 4444);
		run(client);
		client.stopConnection();
	}
}