package de.h_da.verteiltesysteme.sensor;

import org.json.JSONObject;

public class SensorData {
    private long timestamp;
    private String sensorName;
    private SensorTyp sensorTyp;
    private float value;

    public SensorData(String sensorName, SensorTyp sensorTyp, float value){
        this.timestamp = System.currentTimeMillis();
        this.sensorName = sensorName;
        this.sensorTyp = sensorTyp;
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSensorName() {
        return sensorName;
    }

    public SensorTyp getSensorTyp() {
        return sensorTyp;
    }

    public float getValue() {
        return value;
    }

    public JSONObject getJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("timestamp", timestamp);
        jsonObject.put("name", sensorName);
        jsonObject.put("sensor_type", sensorTyp.getTyp());
        jsonObject.put("value", value);

        return jsonObject;
    }
}
