package de.h_da.verteiltesysteme.anbieter;

import de.h_da.verteiltesysteme.anbieter.thrift.ThriftClient;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ThriftClient thriftClient = new ThriftClient();
        Thread.sleep(1000);
        thriftClient.start();
    }
}
