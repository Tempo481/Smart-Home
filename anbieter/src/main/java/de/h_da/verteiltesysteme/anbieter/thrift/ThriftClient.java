package de.h_da.verteiltesysteme.anbieter.thrift;

import de.h_da.verteiltesysteme.anbieter.db.Database;
import de.h_da.verteiltesysteme.anbieter.db.SensorData;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.ArrayList;
import java.util.List;

public class ThriftClient extends Thread {

    @Override
    public void run() {
        TSocket transport = null;
        SensorThrift.Client client = null;

        for(int retry = 0; retry < 30; retry++){
            try {
                transport = new TSocket("172.20.0.2", 9090);
                transport.open();

                TProtocol protocol = new TBinaryProtocol(transport);
                client = new SensorThrift.Client(protocol);
            }catch (TException e){
                System.out.println(String.format("Konnte die Verbindung zum Anbieter nicht herstellen, versuche es in 30sek erneut (%s/30)", retry));
                e.printStackTrace();
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if(client == null){
            throw new RuntimeException("Nach 30 Versuchen konnte keine Verbindung hergestellt werden!");
        }

        int retry = 10;

        while(retry > 0){
            try {
                perform(client);
                Thread.sleep(3000);
            } catch (TException | InterruptedException x) {
                x.printStackTrace();
                retry --;
            }
        }

        if(transport != null){
            transport.close();
        }
    }

    private static void perform(SensorThrift.Client client) throws TException {
        List<SensorDataThrift> sensorDataThriftArrayList = client.getSensorDataList();

        List<SensorData> sensorDataList = new ArrayList<>();

        for (SensorDataThrift sensorDataThrift : sensorDataThriftArrayList) {
            sensorDataList.add(new SensorData(
                sensorDataThrift.getTypeOfSensor(),
                sensorDataThrift.getNameOfSensor(),
                sensorDataThrift.getTimestamp(),
                sensorDataThrift.getValue()));
        }

        System.out.println(String.format("%s new Entries found", sensorDataList.size()));
        Database databaseInstance = Database.getInstance();
        databaseInstance.connect();
        databaseInstance.insertManySensorData(sensorDataList);
    }

}
