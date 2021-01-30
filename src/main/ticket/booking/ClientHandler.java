package main.ticket.booking;

import java.io.*;

import java.net.*;
import java.util.List;

import main.ticket.booking.dao.EventDAO;
import main.ticket.booking.dao.EventImpl;
import main.ticket.booking.dao.MovieDAO;
import main.ticket.booking.dao.MovieImpl;
import main.ticket.booking.pojo.Booking;
import main.ticket.booking.pojo.Event;
import main.ticket.booking.pojo.Movie;
import main.ticket.booking.service.BookingService;

class ClientHandler extends Thread {
    final BufferedReader in;
    final PrintWriter out;
    final Socket clientSocket;

    // Constructor.
    public ClientHandler(Socket clientSocket, BufferedReader in, PrintWriter out) {
        this.clientSocket = clientSocket;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
    	EventDAO eventDAO = new EventImpl();
    	BookingService bookingService = new BookingService();

    	try {
	    	String inputLine;
	    	while ((inputLine = in.readLine()) != null) {
	    		System.out.println("Handling client request: " + inputLine);
	    		String[] array = inputLine.split(",");

	    		switch(array[0]) {
	    			case "send_bookings":
	    				String guestName = array[1];
	    				String bookingsInfo = "";
	    				List<Booking> bookings = bookingService.getBookingsForGuest(guestName);
	    				if (bookings == null || bookings.size() == 0) {
	    					bookingsInfo = "false:  No bookings were made by guest " + guestName;
	    				}
	    				else {
	    					bookingsInfo += "true:";
		    				for (Booking booking : bookings) {
		    					bookingsInfo += booking.getId() + "," + booking.getEventId() + "," + booking.getGuestName() + ","
		    				                  + booking.getTicketsBooked() + "," + booking.getTicketsValueTotal() + ";";
		    				}
		    				bookingsInfo = bookingsInfo.substring(0, bookingsInfo.length() - 1);
	    				}
	    				out.println(bookingsInfo);
	    				break;

	    			case "send_events":
	    				String eventsInfo = "";
						List<Event> events = eventDAO.getAllEvents();
						if (events == null || events.size() == 0) {
							eventsInfo = "false:  There are no events at the moment";
						}
						else {
							eventsInfo = "true:";
							MovieDAO movieDAO = new MovieImpl();
							for (Event event : events ) {
								Movie movie = movieDAO.getMovie(event.getMovieId());
								eventsInfo += event.getId() + "," + movie.getTitle() + "," + event.getScreenId() + "," + event.getDateTimeInMillis() + ","
										    + event.getTicketPrice() + "," + event.getTicketsAvailable() + ";";
							}
							eventsInfo = eventsInfo.substring(0, eventsInfo.length() - 1);
						}
						out.println(eventsInfo);
	    				break;

	    			case "book":
	    				Event event = eventDAO.getEvent(Integer.parseInt(array[1]));
	    				if (event == null) {
	    					out.println("  Selected event does not exist.");
	    					break;
	    				}

	    				Booking booking = new Booking(Integer.parseInt(array[1]), array[2], Integer.parseInt(array[3]), Integer.parseInt(array[3]) * event.getTicketPrice());

	    				int bookingId = bookingService.makeBooking(booking);
						if (bookingId > 0) {
							out.println("  Booking reference id: " + bookingId);
						}
						else {
							out.println("  Could not register your booking. Please try again.");
						}
						break;

	    			case "cancel":
	    				boolean hasCanceled = bookingService.cancelBooking(Integer.parseInt(array[1]));
	    				if (hasCanceled) {
	    					out.println("  Booking successfully cancelled");
	    				}
	    				else {
	    					out.println("  Could not cancel your booking. Please try again.");
	    				}
	    				break;

	    			case "bye":
	    				out.println("  Good bye");
	    				break;

	    			default:
	    				out.println("  Unknown request");
	    				break;
	    		}
	    	}

    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
}