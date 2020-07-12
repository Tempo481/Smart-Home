package de.h_da.verteiltesysteme.zentrale.sensor;

public class Sensor {

    public Sensor(String typeOfSensor, String nameOfSensor, float value, long timestamp, long sequenceNumber) {
        this.typeOfSensor = typeOfSensor;
        this.nameOfSensor = nameOfSensor;
        this.value = value;
        this.timestamp = timestamp;
        this.sequenceNumber = sequenceNumber;
    }

    public String getTypeOfSensor() {
        return typeOfSensor;
    }

    public String getNameOfSensor() {
        return nameOfSensor;
    }

    public float getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getSequenceNumber() {
        return sequenceNumber;
    }

    private String typeOfSensor;
    private String nameOfSensor;
    private float value;
    private long timestamp;
    private long sequenceNumber;
}
