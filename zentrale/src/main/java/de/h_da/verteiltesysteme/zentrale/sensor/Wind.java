package de.h_da.verteiltesysteme.zentrale.sensor;

import java.sql.Timestamp;

public class Wind {

    public Wind(float windspeed, Timestamp timestamp, String typeOfSensor, String nameOfSensor) {
        this.windspeed = windspeed;
        this.timestamp = timestamp;
        this.typeOfSensor = typeOfSensor;
        this.nameOfSensor = nameOfSensor;
    }

    @Override
    public String toString() {
        return "WindSpeed{" +
                "windspeed=" + windspeed +
                ", timestamp=" + timestamp +
                ", typeOfSensor='" + typeOfSensor + '\'' +
                ", nameOfSensor='" + nameOfSensor + '\'' +
                '}';
    }

    private float windspeed;
    private Timestamp timestamp;
    private String typeOfSensor = "Temperature";
    private String nameOfSensor;
}
