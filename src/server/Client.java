package server;

import domain.users.Employee;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket s;
    private final Employee e;

    private final String address;
    private final int port;

    private OutputStream output;
    private PrintWriter writer;
    private InputStream input;
    private BufferedReader reader;

    public Client(Employee e, String address, int port) {
        this.e = e;
        this.address = address;
        this.port = port;
    }

    public void disconnect() throws IOException {
        writer.println("disconnect");
        reader.close();
        output.close();
        writer.close();
        input.close();
        s.close();
    }

    public void sendMessage(String s) {
        writer.println(s);
        System.out.println("Sent message to server.");
    }

    public void read() throws IOException {
        String serverResponse = reader.readLine();
        if (serverResponse != null) {
            System.out.println("Server says: " + serverResponse);
        }
    }

    public void login() {
        System.out.println("Sending login message to " + address + ":" + port);
        writer.println("login:" + EmployeeFactory.toString(e));
    }

    public void connect() throws IOException {
        s = new Socket(address, port);

        System.out.println("Connected to server on " + address + ":" + port);

        // Set up input and output streams for communication
        output = s.getOutputStream();
        writer = new PrintWriter(output, true);
        input = s.getInputStream();
        reader = new BufferedReader(new InputStreamReader(input));
        login();
    }

}
