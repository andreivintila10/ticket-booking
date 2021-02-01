package ticket.booking;

import java.io.*;

import java.net.*;

import ticket.booking.networking.Message;

// Client class.
public class Client {
	private Socket clientSocket;
	private PrintWriter out;
    private BufferedReader in;

    // Method to establish a connection.
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    // Method to send a message object as Json string.
    public String sendMessage(Message message) throws IOException {
        out.println(message.toString());
        String resp = in.readLine();
        return resp;
    }

    // Method to stop connection.
    public void stopConnection() throws IOException {
        in.close();
	    out.close();
	    clientSocket.close();
    }

}