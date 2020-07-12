package de.h_da.verteiltesysteme.anbieter;

import de.h_da.verteiltesysteme.anbieter.thrift.ThriftClient;
import de.h_da.verteiltesysteme.anbieter.thrift.ThriftServer;

public class Main {

    public static void main(String[] args){

        ThriftServer thriftServer = new ThriftServer();
        thriftServer.start();


        ThriftClient thriftClient = new ThriftClient();
        thriftClient.start();
    }
}
