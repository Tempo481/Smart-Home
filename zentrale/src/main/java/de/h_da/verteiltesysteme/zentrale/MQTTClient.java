package de.h_da.verteiltesysteme.zentrale;

import de.h_da.verteiltesysteme.zentrale.sensor.Brightness;
import de.h_da.verteiltesysteme.zentrale.sensor.Rainfall;
import de.h_da.verteiltesysteme.zentrale.sensor.Sensor;
import de.h_da.verteiltesysteme.zentrale.sensor.SensorData;
import de.h_da.verteiltesysteme.zentrale.sensor.Temperature;
import de.h_da.verteiltesysteme.zentrale.sensor.Wind;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.UUID;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

public class MQTTClient implements SensorData {
    private static final String TOPIC = "vs_sensoren";

    private IMqttClient mqttClient;

    public MQTTClient(String hostname, int port) throws MqttException {
        String publisherId = UUID.randomUUID().toString();
        mqttClient = new MqttClient(String.format("tcp://%s:%s", hostname, port),publisherId);
    }

    public void connect() throws MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10);
        mqttClient.connect(options);

        mqttClient.subscribe(TOPIC, this::handleMessage);
    }

    private void handleMessage(String topic, MqttMessage message){
        String payload = new String(message.getPayload());

        System.out.println("[Payload received](" + SENSOR_ARRAY_LIST.size() + " queued)" + payload);
        parseJSON(payload);
    }


    /*
    * Das Nachrichtenformat ist JSON, also wird der String aus dem UDP-Paket
    * in ein JSON Objekt konvertiert, um dann die Attribute auszulesen
    * und in die Container aus der Klasse CentralComputer zu speichern.
    */
    public static void parseJSON(String str) {
        JSONObject obj = new JSONObject(str);
        long timestampLong = obj.getLong("timestamp");
        String name = obj.getString("name");
        String sensor_type = obj.getString("sensor_type");
        float value = obj.getInt("value");
        System.out.println(timestampLong + " " + name + " " + sensor_type + " " + value);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timestampLong),
            TimeZone.getDefault().toZoneId());

        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        Sensor sensor = new Sensor(sensor_type, name, value, timestampLong);
        SENSOR_ARRAY_LIST.add(sensor);

        switch (sensor_type) {
            case "Temperatur": {
                Temperature temperature = new Temperature(value, timestamp, sensor_type, name);
                TEMPERATURE_SENSOR_DATA.add(temperature);
                break;
            }
            case "Helligkeit": {
                Brightness brightness = new Brightness(value, timestamp, sensor_type, name);
                BRIGHTNESS_SENSOR_DATA.add(brightness);
                break;
            }
            case "Wind": {
                Wind wind = new Wind(value, timestamp, sensor_type, name);
                WINDSPEED_SENSOR_DATA.add(wind);
                break;
            }
            case "Niederschlag": {
                Rainfall rainfall = new Rainfall(value, timestamp, sensor_type, name);
                RAINFALL_SENSOR_DATA.add(rainfall);
                break;
            }
        }
    }
}