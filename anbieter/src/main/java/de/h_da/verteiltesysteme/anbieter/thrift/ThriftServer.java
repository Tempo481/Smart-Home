package de.h_da.verteiltesysteme.anbieter.thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class ThriftServer extends Thread {

    public static SensorThriftHandler handler;

    public static SensorThrift.Processor processor;

    @Override
    public void run() {
        try {
            handler = new SensorThriftHandler();
            processor = new SensorThrift.Processor(handler);
            simple(processor);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void simple(SensorThrift.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);

            // Use this for a multithreaded server
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
