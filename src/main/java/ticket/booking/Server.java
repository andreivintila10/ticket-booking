package ticket.booking;

import java.io.*;
import java.net.*;

public class Server {

	private ServerSocket serverSocket;

	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);

        // Running infinite loop for getting client request.
        while (true) {
            Socket clientSocket = null;

            try {
                // Socket object to receive incoming client requests.
            	clientSocket = serverSocket.accept();

                System.out.println("A new client has connected: " + clientSocket);

                // Obtaining input and out streams.
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                System.out.println("Assigning new thread for this client");

                // Create a new thread object.
                Thread thread = new ClientHandler(clientSocket, in, out);

                // Invoking the start() method.
                thread.start();

            } catch (Exception e) {
            	clientSocket.close();
                e.printStackTrace();
            }
        }
	}

	public void stop() throws IOException {
        serverSocket.close();
    }

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start(4444);  // Server is listening on port 4444.
    }
}