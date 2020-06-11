package de.h_da.verteiltesysteme.zentrale.sensor;

public class Sensor {

    public Sensor(String typeOfSensor, String nameOfSensor, float value, long timestamp) {
        this.typeOfSensor = typeOfSensor;
        this.nameOfSensor = nameOfSensor;
        this.value = value;
        this.timestamp = timestamp;
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

    private String typeOfSensor;
    private String nameOfSensor;
    private float value;
    private long timestamp;
}
