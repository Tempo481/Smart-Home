package de.h_da.verteiltesysteme.zentrale;

public class Main {

    public static void main(String[] args) {
        UDPServer udpServer = new UDPServer();
        udpServer.start();

        TCPServer tcpServer = new TCPServer();
        tcpServer.start();
    }
}
