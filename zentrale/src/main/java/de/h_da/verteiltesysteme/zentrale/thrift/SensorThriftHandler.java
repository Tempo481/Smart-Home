package de.h_da.verteiltesysteme.zentrale.thrift;

import de.h_da.verteiltesysteme.zentrale.sensor.Sensor;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

import static de.h_da.verteiltesysteme.zentrale.sensor.SensorData.SENSOR_ARRAY_LIST;

public class SensorThriftHandler implements SensorThrift.Iface {

    @Override
    public void ping() throws TException {
        System.out.println("ping() zentrale Thrift");
    }

    @Override
    public List<SensorDataThrift> getSensorDataList() throws TException {

        if (SENSOR_ARRAY_LIST.size() < 12) {
            System.out.println("SENSOR_ARRAY_LIST has size " + SENSOR_ARRAY_LIST.size());
            return null;
        } else {
            ArrayList<SensorDataThrift> sensorDataThriftArrayList = new ArrayList<>();

            for (Sensor sensor : SENSOR_ARRAY_LIST) {
                SensorDataThrift sensorDataThrift = new SensorDataThrift (sensor.getNameOfSensor(),
                        sensor.getTypeOfSensor(), sensor.getTimestamp(), sensor.getValue());
                sensorDataThriftArrayList.add(sensorDataThrift);
            }
            SENSOR_ARRAY_LIST.clear();
            return sensorDataThriftArrayList;
        }
    }
}
