package ticket.booking;

import java.io.*;

import java.net.*;

import org.json.JSONObject;

import ticket.booking.networking.BookRequestMsg;
import ticket.booking.networking.BookRequestMsgImpl;
import ticket.booking.networking.BookingsRequestMsg;
import ticket.booking.networking.BookingsRequestMsgImpl;
import ticket.booking.networking.CancelRequestMsg;
import ticket.booking.networking.CancelRequestMsgImpl;
import ticket.booking.networking.EventsRequestMsg;
import ticket.booking.networking.EventsRequestMsgImpl;
import ticket.booking.networking.ExitRequestMsg;
import ticket.booking.networking.ExitRequestMsgImpl;
import ticket.booking.networking.ExitResponseMsgImpl;
import ticket.booking.networking.Message;
import ticket.booking.networking.UnknownResponseMsgImpl;
import ticket.booking.networking.factory.BookResponseMsgFactory;
import ticket.booking.networking.factory.BookingsResponseMsgFactory;
import ticket.booking.networking.factory.CancelResponseMsgFactory;
import ticket.booking.networking.factory.EventsResponseMsgFactory;
import ticket.booking.networking.factory.MessageFactory;


// ClientHandler class.
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

    // Method to send response message back to client.
    public void sendMessage(Message message) {
        out.println(message.toString());
    }

    // Method to deal with all client requests within client-server session.
    @Override
    public void run() {

    	try {
    	    // Declaring some variables that will be reused among each request.
    	    JSONObject jsonRequest;
    	    MessageFactory messageFactory;
	    	String inputLine;

	    	// While the session is still alive keep retrieving processes from client.
	    	while ((inputLine = in.readLine()) != null) {
	    		System.out.println("Handling client request: " + inputLine);
	    		jsonRequest = new JSONObject(inputLine);

	    		/* For the ClientHandler, each request will be expecting the desired message object.
	    		   We cannot declare the request message as the base interface Message because there
	    		   haven't been specified any accessor methods to retrieve the request parameters.
	    		   In turn, because we just need to create it, the response message can be declared 
	    		   as type Message. 
	    		   For requests which require extra logic to construct the response message,
	    		   for example: book, cancel, show_events. A factory class is used to hide the 
	    		   server side functionality behind an extra layer of abstraction. */
	    		// Identify the type of request.
	    		switch(jsonRequest.getString("type")) {

	    		    // Handling the client request to show their bookings.
	    			case "show_bookings": BookingsRequestMsg bookingsRequest = new BookingsRequestMsgImpl();
	    			                      bookingsRequest.parseJSONString(inputLine);

	    			                      messageFactory = new BookingsResponseMsgFactory(bookingsRequest.getGuestName());
	    			                      Message bookingsResponse = messageFactory.makeMessage();
	    			                      bookingsResponse.createMessage();
	    			                      sendMessage(bookingsResponse);
	    			                      break;

	    			// Handling the client request to show current events.
	    			case "show_events":   EventsRequestMsg eventsRequest = new EventsRequestMsgImpl();
	    			                      eventsRequest.parseJSONString(inputLine);

	    			                      messageFactory = new EventsResponseMsgFactory();
	    			                      Message eventsResponse = messageFactory.makeMessage();
	    			                      eventsResponse.createMessage();
	    			                      sendMessage(eventsResponse);
	    			                      break;

	    			// Handling the client request to book a ticket for a movie event.
	    			case "book":          BookRequestMsg bookRequest = new BookRequestMsgImpl();
                                          bookRequest.parseJSONString(inputLine);

                                          messageFactory = new BookResponseMsgFactory(bookRequest.getEventId(), bookRequest.getTicketsBooked(),
                                                                                      bookRequest.getGuestName());
                                          Message bookResponse = messageFactory.makeMessage();
                                          bookResponse.createMessage();
                                          sendMessage(bookResponse);
                                          break;

                    // Handling the client request to cancel one of their bookings.
	    			case "cancel":        CancelRequestMsg cancelRequest = new CancelRequestMsgImpl();
            	    			          cancelRequest.parseJSONString(inputLine);

            	    			          messageFactory = new CancelResponseMsgFactory(cancelRequest.getBookingId());
            	    			          Message cancelResponse = messageFactory.makeMessage();
            	    			          cancelResponse.createMessage();
            	    			          sendMessage(cancelResponse);
            	    			          break;

            	    // Handling the client request to stop serving requests.
	    			case "bye":           ExitRequestMsg exitRequest = new ExitRequestMsgImpl();
                                          exitRequest.parseJSONString(inputLine);

                                          Message exitResponse = new ExitResponseMsgImpl();
                                          exitResponse.createMessage();
                                          sendMessage(exitResponse);
                                          break;

                    // Handling unknown client requests.
	    			default:              Message unknownResponse = new UnknownResponseMsgImpl();
                                          unknownResponse.createMessage();
                                          sendMessage(unknownResponse);
                                          break;
	    		}
	    	}

    	} catch(IOException e) {  // Catch any IOException.
    		e.printStackTrace();
    	}
    }
}