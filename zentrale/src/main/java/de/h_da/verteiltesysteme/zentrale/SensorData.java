package de.h_da.verteiltesysteme.zentrale;

import java.util.ArrayList;

public interface SensorData {
    ArrayList<Temperature> TEMPERATURE_SENSOR_DATA = new ArrayList<>();
    ArrayList<Brightness> BRIGHTNESS_SENSOR_DATA = new ArrayList<>();
    ArrayList<Wind> WINDSPEED_SENSOR_DATA = new ArrayList<>();
    ArrayList<Rainfall> RAINFALL_SENSOR_DATA = new ArrayList<>();
}
