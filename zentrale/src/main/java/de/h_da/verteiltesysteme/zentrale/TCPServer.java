package de.h_da.verteiltesysteme.zentrale;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread {

    @Override
    public void run() {
        try {
            listenOnTCPSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Es wird auf Clients gewartet, und falls eine Anfrage ankommt
    * wird ein neuer Thread gestartet um diese Anfrage abzuarbeiten.
    * Dies ist notwendig, damit mehrere Clients gleichzeitig bedient werden können.
    */
    public static void listenOnTCPSocket() throws IOException {
        System.out.println("listening on TCP Socket ...");

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected ...");
            new TCPServerThread(socket).start();
        }
    }
    private static final int port = 80;
}

//http://localhost/index/

