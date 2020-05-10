package de.h_da.verteiltesysteme.sensor;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Simple UDP Send Client
 */
public class UDPClient {
    //Socket to send data through
    private DatagramSocket socket;

    //Address of the Server
    private InetAddress address;
    //Port of the Server
    private int port;

    /**
     * UDPClient constructor
     * @param hostname hostname of the server
     * @param port port of the server
     * @throws UnknownHostException error on unknown host
     * @throws SocketException other socket error
     */
    public UDPClient(String hostname, int port) throws UnknownHostException, SocketException {
        this(InetAddress.getByName(hostname), port);
    }

    public UDPClient(InetAddress address, int port) throws SocketException {
        this.address = address;
        this.port = port;
        this.socket = new DatagramSocket();
    }

    /**
     * Sends data to the given server
     * @param data data to send
     * @throws IOException error on transmission
     */
    public void sendData(String data) throws IOException {
        byte[] buffer = data.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
    }
}
