package de.h_da.verteiltesysteme.anbieter.thrift;

import org.apache.thrift.TException;

import java.util.List;

public class SensorThriftHandler implements SensorThrift.Iface {

    @Override
    public void ping() throws TException {
        System.out.println("ping() Thrift anbieter");
    }

    @Override
    public List<SensorDataThrift> getSensorDataList() throws TException {
        return null;
    }
}
