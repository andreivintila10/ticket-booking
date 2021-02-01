package ticket.booking.networking;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import ticket.booking.pojo.Booking;

// BookingsResponseMsgImpl class for the show bookings response message.
public class BookingsResponseMsgImpl implements BookingsResponseMsg {
    private int status;
    private List<Booking> bookings;
    private JSONObject jsonResponse;

    // Empty constructor.
    public BookingsResponseMsgImpl() {
        this.status = 0;
        this.bookings = new ArrayList<Booking>();
        this.jsonResponse = new JSONObject();
    }

    // Parametrised Constructor.
    public BookingsResponseMsgImpl(List<Booking> bookings) {
        this.status = bookings.size() > 0 ? 1 : 0;
        this.bookings = bookings;
        this.jsonResponse = new JSONObject();
    }

    // Create the json response message.
    @Override
    public void createMessage() {
        JSONObject responseData = new JSONObject();

        JSONArray jsonBookings = new JSONArray();
        JSONObject jsonBooking = null;
        for (Booking booking : bookings) {
            jsonBooking = new JSONObject();

            jsonBooking.put("booking_id", booking.getId());
            jsonBooking.put("event_id", booking.getEventId());
            jsonBooking.put("guest_name", booking.getGuestName());
            jsonBooking.put("tickets_booked", booking.getTicketsBooked());
            jsonBooking.put("tickets_value_total", booking.getTicketsValueTotal());

            jsonBookings.put(jsonBooking);
        }

        responseData.put("bookings", jsonBookings);
        jsonResponse.put("data", responseData);
        jsonResponse.put("status", status);
    }

    // Factory method to construct the message object by parsing the response Json string.
    @Override
    public void parseJSONString(String jsonString) {
        jsonResponse = new JSONObject(jsonString);
        status = jsonResponse.getInt("status");

        JSONObject responseData = jsonResponse.getJSONObject("data");

        JSONObject jsonBooking = null;
        JSONArray jsonBookings = responseData.getJSONArray("bookings");

        Booking theBooking;
        List<Booking> theBookings = new ArrayList<Booking>();
        for (int index = 0; index < jsonBookings.length(); index++) {
            jsonBooking = jsonBookings.getJSONObject(index);
            theBooking = new Booking(jsonBooking.getInt("booking_id"), jsonBooking.getInt("event_id"), jsonBooking.getString("guest_name"),
                                  jsonBooking.getInt("tickets_booked"), jsonBooking.getInt("tickets_value_total"));

            theBookings.add(theBooking);
        }

        bookings = theBookings;
    }

    // Accessor method implementation for status.
    @Override
    public int getStatus() {
        return status;
    }

    // Accessor method implementation for bookings.
    @Override
    public List<Booking> getBookings() {
        return bookings;
    }

    // Method toString implementation.
    @Override
    public String toString() {
        return jsonResponse.toString();
    }

}
