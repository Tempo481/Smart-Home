package de.h_da.verteiltesysteme.zentrale;

import java.sql.Timestamp;

public class Brightness {

    public Brightness(Timestamp timestamp, float brightness, String typeOfSensor, String nameOfSensor) {
        this.timestamp = timestamp;
        this.brightness = brightness;
        this.typeOfSensor = typeOfSensor;
        this.nameOfSensor = nameOfSensor;
    }

    @Override
    public String toString() {
        return "Brightness{" +
                "timestamp=" + timestamp +
                ", brightness=" + brightness +
                ", typeOfSensor='" + typeOfSensor + '\'' +
                ", nameOfSensor='" + nameOfSensor + '\'' +
                '}';
    }

    private Timestamp timestamp;
    private float brightness;
    private String typeOfSensor = "Brightness";
    private String nameOfSensor;
}
