package de.h_da.verteiltesysteme.anbieter.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.ArrayList;
import java.util.List;

public class ThriftClient extends Thread {

    @Override
    public void run() {
        try {
            TTransport transport;
            transport = new TSocket("172.20.0.2", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            SensorThrift.Client client = new SensorThrift.Client(protocol);

            while (true) {
                perform(client);
                Thread.sleep(3000);
            }
//            transport.close();
        } catch (TException | InterruptedException x) {
            x.printStackTrace();
        }
    }

    private static void perform(SensorThrift.Client client) throws TException {
        try {
            List<SensorDataThrift> sensorDataThriftArrayList = client.getSensorDataList();
            if( sensorDataThriftArrayList.size() < 1) {
                System.out.println("sensorDataThriftArrayList has size " + sensorDataThriftArrayList.size());
            } else {
                for (SensorDataThrift sensorDataThrift : sensorDataThriftArrayList) {
                    System.out.println(sensorDataThrift.toString());
                }
            }
        } catch (TException e) {
            e.printStackTrace();
        }
    }

}
