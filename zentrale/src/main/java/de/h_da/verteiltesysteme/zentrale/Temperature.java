package de.h_da.verteiltesysteme.zentrale;

import java.sql.Timestamp;

public class Temperature {

    public Temperature(float temperatur, Timestamp timestamp, String typeOfSensor, String nameOfSensor) {
        this.temperatur = temperatur;
        this.timestamp = timestamp;
        this.typeOfSensor = typeOfSensor;
        this.nameOfSensor = nameOfSensor;
    }

    @Override
    public String toString() {
        return "Temperatur{" +
                "temperatur=" + temperatur +
                ", timestamp=" + timestamp +
                ", typeOfSensor='" + typeOfSensor + '\'' +
                ", nameOfSensor='" + nameOfSensor + '\'' +
                '}';
    }

    private float temperatur;
    private Timestamp timestamp;
    private String typeOfSensor = "Temperature";
    private String nameOfSensor;
}
