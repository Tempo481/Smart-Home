package de.h_da.verteiltesysteme.zentrale;

import de.h_da.verteiltesysteme.zentrale.thrift.ThriftServer;

public class Main {

    public static void main(String[] args) {
        UDPServer udpServer = new UDPServer();
        udpServer.start();

        TCPServer tcpServer = new TCPServer();
        tcpServer.start();

        ThriftServer thriftServer = new ThriftServer();
        thriftServer.start();
    }
}
