package de.h_da.verteiltesysteme.zentrale;

public class Main {

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++){
//            System.out.println(String.format("Zentrale funktioniert %s", i));
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        UDPServer udpServer = new UDPServer();
        udpServer.start();

        TCPServer tcpServer = new TCPServer();
        tcpServer.start();
    }
}
