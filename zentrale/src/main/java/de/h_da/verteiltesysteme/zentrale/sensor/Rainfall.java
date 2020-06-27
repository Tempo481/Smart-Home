package de.h_da.verteiltesysteme.zentrale.sensor;

import java.sql.Timestamp;

public class Rainfall {

    public Rainfall(float rainfall, Timestamp timestamp, String typeOfSensor, String nameOfSensor) {
        this.rainfall = rainfall;
        this.timestamp = timestamp;
        this.typeOfSensor = typeOfSensor;
        this.nameOfSensor = nameOfSensor;
    }

    @Override
    public String toString() {
        return "Rainfall{" +
                "rainfall=" + rainfall +
                ", timestamp=" + timestamp +
                ", typeOfSensor='" + typeOfSensor + '\'' +
                ", nameOfSensor='" + nameOfSensor + '\'' +
                '}';
    }

    private float rainfall;
    private Timestamp timestamp;
    private String typeOfSensor = "Rainfall";
    private String nameOfSensor;
}
