package de.h_da.verteiltesysteme.zentrale.sensor;

import java.sql.Timestamp;

public class Brightness {

    public Brightness(float brightness, Timestamp timestamp, String typeOfSensor, String nameOfSensor) {
        this.brightness = brightness;
        this.timestamp = timestamp;
        this.typeOfSensor = typeOfSensor;
        this.nameOfSensor = nameOfSensor;
    }

    @Override
    public String toString() {
        return "Brightness{" +
                "brightness=" + brightness +
                ", timestamp=" + timestamp +
                ", typeOfSensor='" + typeOfSensor + '\'' +
                ", nameOfSensor='" + nameOfSensor + '\'' +
                '}';
    }

    private float brightness;
    private Timestamp timestamp;
    private String typeOfSensor = "Brightness";
    private String nameOfSensor;
}
