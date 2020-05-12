package de.h_da.verteiltesysteme.zentrale;

import de.h_da.verteiltesysteme.zentrale.db.DatabaseTester;

public class Main {

    public static void main(String[] args) {

        DatabaseTester databaseTester = new DatabaseTester();
        databaseTester.main();

        UDPServer udpServer = new UDPServer();
        udpServer.start();

        TCPServer tcpServer = new TCPServer();
        tcpServer.start();
    }
}
