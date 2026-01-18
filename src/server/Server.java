package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Create main server for chat
 * Create join, disconnect functions for chat
 * Create class that represents user in the chat
 * Make messaging system
 * */



public class Server {
    private static final int PORT = 5001;
    private final List<User> employees = new ArrayList<>();
    private boolean closeServer = false;
    public void removeClient(User c) {
        employees.remove(c);
        System.out.println("Disconnected " + c.e);
        if (employees.isEmpty()) closeServer = true;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setReuseAddress(true);
            System.out.println("Chat has started on port: " + PORT);
            while(!closeServer)
            {
                Socket clientSocket = serverSocket.accept();

                User client = new User(clientSocket, this);
                new Thread(client).start();
                System.out.println("Employee " + client.e + " joined");
                boolean contains = false;
                for (User c : employees) {
                    if (c.e.getId().equals(client.e.getId())) {
                        contains = true;
                        break;
                    }
                }
                if (!contains) {
                    employees.add(client);

                } else {

                    removeClient(client);
                    System.out.println("Employee " + client.e + " Is already in connected!");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public synchronized void broadcast(String i_Message, User cl) {
        for(User receiver : employees)
        {
            if (!receiver.e.getId().equals(cl.e.getId())) {
                receiver.SendMessage(cl.e.getName() + "-" + cl.e.getId() + ":" + i_Message);
            }
        }
    }
}