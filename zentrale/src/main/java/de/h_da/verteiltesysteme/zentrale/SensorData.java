package de.h_da.verteiltesysteme.zentrale;

import java.util.ArrayList;

public interface SensorData {
    public static ArrayList<Temperature> TEMPERATURE_SENSOR_DATA = new ArrayList<>();
    public static ArrayList<Brightness> BRIGHTNESS_SENSOR_DATA = new ArrayList<>();
    public static ArrayList<Wind> WINDSPEED_SENSOR_DATA = new ArrayList<>();
    public static ArrayList<Rainfall> RAINFALL_SENSOR_DATA = new ArrayList<>();
}
