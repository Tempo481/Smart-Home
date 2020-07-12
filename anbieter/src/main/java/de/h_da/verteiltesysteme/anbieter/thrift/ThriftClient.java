package de.h_da.verteiltesysteme.anbieter.thrift;

import de.h_da.verteiltesysteme.anbieter.db.Database;
import de.h_da.verteiltesysteme.anbieter.db.SensorData;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;

import java.util.ArrayList;
import java.util.List;

public class ThriftClient extends Thread {

    private long sequenceNumber;
    private long timestamp;

    @Override
    public void run() {
        TSocket transport = null;
        SensorThrift.Client client = null;

        for(int retry = 0; retry < 30; retry++){
            try {
                transport = new TSocket(System.getenv("ZENTRALE_IP"), 9090);
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
            System.exit(-1);
            throw new RuntimeException("Nach 30 Versuchen konnte keine Verbindung hergestellt werden!");
        }

        int retry = 10;

        init();

        while(retry > 0){
            try {
                perform(client);
                Thread.sleep(3000);
            } catch (TException | InterruptedException x) {
                x.printStackTrace();
                init();
                retry --;
            }
        }

        if(transport != null){
            transport.close();
        }
    }

    private void init() {
        //Get own last sequence and timestamp
        long ownSequenceNumber = Database.getInstance().getLastSequenceNumber();

        long ownLastTimestamp = Database.getInstance().getLastTimestamp();

        //Request Datasets from other Database instances
        String[] ips = new String[]{System.getenv("ANBIETER_A_IP"),System.getenv("ANBIETER_B_IP")};
        Integer[] ports = new Integer[]{Integer.parseInt(System.getenv("ANBIETER_A_PORT")),Integer.parseInt(System.getenv("ANBIETER_B_PORT"))};

        List<SensorData> resultingData = new ArrayList<>();
        //Merge Data from the other Database

        long remoteLastSequence = -1;
        long remoteLastTimestamp = -1;
        SensorData sensorData;

        boolean anyData = false;

        //Informationen von den anderen Anbietern anfragen
        SensorThrift.Client client;
        for(int i = 0; i < ips.length; i++){
            try{
                System.out.println(String.format("Requesting Data from other Anbieter #%s @ %s:%s ...", i, ips[i],ports[i]));
                TSocket transport = new TSocket(ips[i], ports[i]);
                transport.open();

                TProtocol protocol = new TBinaryProtocol(transport);
                client = new SensorThrift.Client(protocol);

                List<SensorDataThrift> receivedData = client
                    .getSensorDataListAfter(ownLastTimestamp, ownSequenceNumber);
                System.out.println(String.format("%s Entries from other Anbieter #%s @ %s:%s received!", receivedData.size(),i, ips[i],ports[i]));

                for(SensorDataThrift sensorDataThrift: receivedData){
                    sensorData = new SensorData(sensorDataThrift);

                    if(!resultingData.contains(sensorData)){
                        resultingData.add(sensorData);

                        if(sensorData.getTimestamp() > remoteLastTimestamp){
                            remoteLastTimestamp = sensorData.getTimestamp();
                        }

                        if(sensorData.getSequenceNumber() > remoteLastSequence){
                            remoteLastSequence = sensorData.getSequenceNumber();
                        }
                    }
                }
                anyData = true;
            }catch (TException e){
                System.out.println(String.format("Requesting Data from other Anieter #%s @ %s:%s failed because of:", i, ips[i], ports[i]));
                e.printStackTrace();
            }
        }

        //Der geringe Fall das max value erreicht wurde und die sequenz nummer von vorne anfängt
        if (remoteLastSequence == Long.MAX_VALUE){
            remoteLastSequence = 0;
            System.out.println("MAX Value was reached sequence Number will be reset and second batch will be loaded...");

            //Informationen von den anderen Anbietern anfragen
            for(int i = 0; i < ips.length; i++){
                try{
                    System.out.println(String.format("Requesting Data from other Anieter #%s @ %s:%s ...", i, ips[i],ports[i]));
                    TSocket transport = new TSocket(ips[i], ports[i]);
                    transport.open();

                    TProtocol protocol = new TBinaryProtocol(transport);
                    client = new SensorThrift.Client(protocol);

                    List<SensorDataThrift> receivedData = client
                        .getSensorDataListAfter(ownLastTimestamp, ownSequenceNumber);
                    System.out.println(String.format("%s Entries from other Anbieter #%s @ %s:%s received!", receivedData.size(),i, ips[i],ports[i]));

                    for(SensorDataThrift sensorDataThrift: receivedData){
                        sensorData = new SensorData(sensorDataThrift);

                        if(!resultingData.contains(sensorData)){
                            resultingData.add(sensorData);

                            if(sensorData.getTimestamp() > remoteLastTimestamp){
                                remoteLastTimestamp = sensorData.getTimestamp();
                            }

                            if(sensorData.getSequenceNumber() > remoteLastSequence){
                                remoteLastSequence = sensorData.getSequenceNumber();
                            }
                        }
                    }
                }catch (TException e){
                    System.out.println(String.format("Requesting Data from other Anieter #%s @ %s:%s failed because of:", i, ips[i], ports[i]));
                    e.printStackTrace();
                }
            }
        }

        if(!anyData){
            System.out.println("Warning other Databases cannot be reached! Whole System seems to be down!");
        }

        System.out.println(String.format("Total Amount of Data received: %s", resultingData.size()));
        Database.getInstance().insertManySensorData(resultingData);

        this.sequenceNumber = remoteLastSequence;
        this.timestamp = remoteLastTimestamp;

        System.out.println(String.format("Sequence Number Check: Local Seq: %s Remote Seq: %s | local Timestamp: %s Remote Timestamp: %s",
            ownSequenceNumber, remoteLastSequence,
            ownLastTimestamp, remoteLastTimestamp));
    }


    private void perform(SensorThrift.Client client) throws TException {
        List<SensorDataThrift> sensorDataThriftArrayList = client.getSensorDataListAfter(this.timestamp, this.sequenceNumber);

        List<SensorData> sensorDataList = new ArrayList<>();

        for (SensorDataThrift sensorDataThrift : sensorDataThriftArrayList) {
            SensorData sensorData = new SensorData(sensorDataThrift);

            if(sensorData.getTimestamp() > this.timestamp){
                this.timestamp = sensorData.getTimestamp();
            }

            if(sensorData.getSequenceNumber() > this.sequenceNumber){
                this.sequenceNumber = sensorData.getSequenceNumber();
            }

            sensorDataList.add(sensorData);
        }

        System.out.println(String.format("%s new Entries found", sensorDataList.size()));
        Database databaseInstance = Database.getInstance();
        databaseInstance.insertManySensorData(sensorDataList);
    }

}
