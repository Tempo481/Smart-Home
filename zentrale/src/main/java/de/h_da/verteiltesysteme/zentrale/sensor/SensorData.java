package de.h_da.verteiltesysteme.zentrale.sensor;

import de.h_da.verteiltesysteme.zentrale.sensor.Brightness;
import de.h_da.verteiltesysteme.zentrale.sensor.Rainfall;
import de.h_da.verteiltesysteme.zentrale.sensor.Temperature;
import de.h_da.verteiltesysteme.zentrale.sensor.Wind;

import java.util.ArrayList;

public interface SensorData {
    ArrayList<Temperature> TEMPERATURE_SENSOR_DATA = new ArrayList<>();
    ArrayList<Brightness> BRIGHTNESS_SENSOR_DATA = new ArrayList<>();
    ArrayList<Wind> WINDSPEED_SENSOR_DATA = new ArrayList<>();
    ArrayList<Rainfall> RAINFALL_SENSOR_DATA = new ArrayList<>();
    ArrayList<Sensor> SENSOR_ARRAY_LIST = new ArrayList<>();
}
