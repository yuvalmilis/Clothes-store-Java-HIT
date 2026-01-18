package server;

import domain.users.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class User implements Runnable {
    private Server server;
    private Socket socket;
    private PrintWriter OutputStream;
    private BufferedReader in;
    public Employee e;

    public User(Socket socket, Server server) throws IOException {
        this.socket =  socket;
        this.server = server;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        OutputStream = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
                if (message.contains("login:")) {
                    this.e = EmployeeFactory.parseString(message.substring(message.indexOf("login:") + "login:".length()));
                    System.out.println("Successfully received employee details: " + e);
                    continue;
                }
                if (message.equalsIgnoreCase("disconnect")) {
                    server.removeClient(this);
                    continue;
                }
                server.broadcast(message, this);
            }
        } catch (IOException e) {
            System.out.println("Client " + this.e.getName() + " disconnected");
        }
    }

    public void SendMessage(String i_Message) {
        OutputStream.println(i_Message);
    }
}