package de.h_da.verteiltesysteme.anbieter.thrift;

import de.h_da.verteiltesysteme.anbieter.db.Database;
import de.h_da.verteiltesysteme.anbieter.db.SensorData;
import java.util.ArrayList;
import org.apache.thrift.TException;

import java.util.List;
import sun.management.Sensor;

public class SensorThriftHandler implements SensorThrift.Iface {

    @Override
    public void ping() throws TException {
        System.out.println("ping() Thrift anbieter");
    }

    @Override
    public List<SensorDataThrift> getNewestSensorDataList() throws TException {
        return null;
    }

    @Override
    public long getLastSequenceNumber() throws TException {
        return Database.getInstance().getLastSequenceNumber();
    }

    @Override
    public List<SensorDataThrift> getSensorDataListAfter(long timestamp, long sequenceNumber){
        System.out.println(String.format("Data request after %s & %s", timestamp, sequenceNumber));

        List<SensorDataThrift> results = new ArrayList<>();
        try{
            for(SensorData sensor: Database.getInstance().getEntriesAfter(sequenceNumber, timestamp)){
                results.add(new SensorDataThrift(
                    sensor.getNameOfSensor(),
                    sensor.getTypeOfSensor(),
                    sensor.getTimestamp(),
                    sensor.getSequenceNumber(),
                    sensor.getValue()
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        System.out.println(String.format("Returned to another Thrift Server seq #%s (%s entries)", sequenceNumber, results.size()));
        return results;
    }

}
