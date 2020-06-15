package de.h_da.verteiltesysteme.anbieter.thrift;

import de.h_da.verteiltesysteme.anbieter.db.Database;
import de.h_da.verteiltesysteme.anbieter.db.SensorData;
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

    private long latestTimestamp;
    @Override
    public void run() {
        TTransport transport = null;

        try {
            transport = new TSocket("172.20.0.2", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            SensorThrift.Client client = new SensorThrift.Client(protocol);

            while (true) {
                perform(client);
                Thread.sleep(3000);
            }
        } catch (TException | InterruptedException x) {
            x.printStackTrace();
        }finally {
            if(transport != null){
                transport.close();
            }
        }
    }

    private static void perform(SensorThrift.Client client) throws TException {
        try {
            List<SensorDataThrift> sensorDataThriftArrayList = client.getSensorDataList();
            if( sensorDataThriftArrayList.size() < 1) {
                System.out.println("sensorDataThriftArrayList has size " + sensorDataThriftArrayList.size());
            } else {

                List<SensorData> sensorDataList = new ArrayList<>();

                for (SensorDataThrift sensorDataThrift : sensorDataThriftArrayList) {
                    sensorDataList.add(new SensorData(
                        sensorDataThrift.getTypeOfSensor(),
                        sensorDataThrift.getNameOfSensor(),
                        sensorDataThrift.getTimestamp(),
                        sensorDataThrift.getValue()));
                    System.out.println(sensorDataThrift.toString());
                }
                Database databaseInstance = Database.getInstance();
                databaseInstance.connect();
                databaseInstance.insertManySensorData(sensorDataList);
            }
        } catch (TException e) {
            e.printStackTrace();
        }
    }

}