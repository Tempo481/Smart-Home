package de.h_da.verteiltesysteme.zentrale.thrift;

import de.h_da.verteiltesysteme.zentrale.sensor.Sensor;
import de.h_da.verteiltesysteme.zentrale.sensor.SequenceNumber;
import java.lang.reflect.Array;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

import static de.h_da.verteiltesysteme.zentrale.sensor.SensorData.SENSOR_ARRAY_LIST;
import static de.h_da.verteiltesysteme.zentrale.sensor.SensorData.LAST_SEQUNCE_NUMBER;

public class SensorThriftHandler implements SensorThrift.Iface {

    @Override
    public void ping() throws TException {
        System.out.println("ping() zentrale Thrift");
    }


    @Override
    public long getLastSequenceNumber() throws TException {
        return LAST_SEQUNCE_NUMBER;
    }

    @Override
    public List<SensorDataThrift> getSensorDataListAfter(long timestamp, long sequenceNumber){
        List<SensorDataThrift> sensorDataThriftList = new ArrayList<>();

        for(Sensor sensor: SENSOR_ARRAY_LIST){
            if(sensor.getSequenceNumber() >= sequenceNumber){
                sensorDataThriftList.add(new SensorDataThrift (
                    sensor.getNameOfSensor(),
                    sensor.getTypeOfSensor(),
                    sensor.getTimestamp(),
                    sensor.getSequenceNumber(),
                    sensor.getValue()
                ));
            }
        }

        return sensorDataThriftList;
    }

    @Override
    public List<SensorDataThrift> getNewestSensorDataList() throws TException {
        ArrayList<SensorDataThrift> sensorDataThriftArrayList = new ArrayList<>();

        if (SENSOR_ARRAY_LIST.size() < 12) {
            System.out.println("SENSOR_ARRAY_LIST has size " + SENSOR_ARRAY_LIST.size());
        } else {


            for (Sensor sensor : SENSOR_ARRAY_LIST) {
                SensorDataThrift sensorDataThrift = new SensorDataThrift (
                    sensor.getNameOfSensor(),
                    sensor.getTypeOfSensor(),
                    sensor.getTimestamp(),
                    sensor.getSequenceNumber(),
                    sensor.getValue()
                    );
                sensorDataThriftArrayList.add(sensorDataThrift);
            }
        }
        return sensorDataThriftArrayList;
    }
}
