package de.h_da.verteiltesysteme.zentrale.db;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.json.JSONObject;

/**
 * Generic Sensor Data Object
 */
public class SensorData {
    //Database Fields
    public static final String FIELD_TYPE_OF_SENSOR = "typeOfSensor";
    public static final String FIELD_NAME_OF_SENSOR = "nameOfSensor";
    public static final String FIELD_TIMESTAMP = "timestamp";
    public static final String FIELD_VALUE = "value";

    private String typeOfSensor;
    private String nameOfSensor;
    private long timestamp;
    private float value;

    //Default Constructor
    public SensorData(String typeOfSensor, String nameOfSensor, long timestamp, float value){
        this.typeOfSensor = typeOfSensor;
        this.nameOfSensor = nameOfSensor;
        this.timestamp = timestamp;
        this.value = value;
    }

    //Database Constructor
    public SensorData(DBObject dbObject){
        this.typeOfSensor = (String)dbObject.get(FIELD_TYPE_OF_SENSOR);
        this.nameOfSensor = (String)dbObject.get(FIELD_NAME_OF_SENSOR);
        this.timestamp = (long)dbObject.get(FIELD_TIMESTAMP);
        this.value = (float)(double)dbObject.get(FIELD_VALUE);
    }

    //JSON Constructor
    public SensorData(JSONObject jsonObject){
        this.nameOfSensor = jsonObject.getString(FIELD_NAME_OF_SENSOR);
        this.typeOfSensor = jsonObject.getString(FIELD_TYPE_OF_SENSOR);
        this.timestamp = jsonObject.getLong(FIELD_TIMESTAMP);
        this.value = jsonObject.getFloat(FIELD_VALUE);
    }

    public String getTypeOfSensor() {
        return typeOfSensor;
    }

    public String getNameOfSensor() {
        return nameOfSensor;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float getValue() {
        return value;
    }

    //Database Exporter
    public DBObject toDatabaseObject(){
        return new BasicDBObject()
            .append(FIELD_TYPE_OF_SENSOR, typeOfSensor)
            .append(FIELD_NAME_OF_SENSOR, nameOfSensor)
            .append(FIELD_TIMESTAMP, timestamp)
            .append(FIELD_VALUE, value);
    }

    //Json Exporter
    public JSONObject toJsonObject(){
        return new JSONObject()
            .append(FIELD_TYPE_OF_SENSOR, typeOfSensor)
            .append(FIELD_NAME_OF_SENSOR, nameOfSensor)
            .append(FIELD_TIMESTAMP, timestamp)
            .append(FIELD_VALUE, value);
    }

    @Override
    public String toString() {
        return "SensorData{" +
            "typeOfSensor='" + typeOfSensor + '\'' +
            ", nameOfSensor='" + nameOfSensor + '\'' +
            ", timestamp=" + timestamp +
            ", value=" + value +
            '}';
    }
}
