package ui;

import server.Server;

public class ServerMain {

    public static void main(String[] args) {
        Server s = new Server();
        s.startServer();
    }
}