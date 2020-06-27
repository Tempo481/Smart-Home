package de.h_da.verteiltesysteme.sensor;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.setLevel(Level.ALL);

        logger.info("Sensor is starting...");

        String sensorName = System.getenv("SENSOR_NAME");
        String sensorTypString = System.getenv("SENSOR_TYPE");
        String serverIp = System.getenv("MQTT_IP");
        String serverPortString = System.getenv("MQTT_PORT");

        logger.info(
            String.format("Sensor Parameter: Name: '%s' Typ: '%s' Server Ip: '%s' Server Port: '%s' ",
            sensorName, sensorTypString, serverIp, serverPortString)
        );

        SensorTyp sensorTyp;
        int serverPort;

        try {
            sensorTyp = SensorTyp.valueOf(sensorTypString);
            serverPort = Integer.parseInt(serverPortString);
        }catch (NullPointerException e){
            logger.warning("Sensor cannot interpret all environment variables");
            logger.warning(e.getMessage());
            throw new RuntimeException(e);
        }

        DataGenerator dataGenerator = new DataGenerator(sensorName, sensorTyp);
        MQTTClient mqttClient;

        try {
            mqttClient = new MQTTClient(serverIp, serverPort);
        } catch (MqttException e) {
            logger.warning("Cannot access Ip Socket, cannot proceed!");
            logger.warning(e.getMessage());
            throw new RuntimeException(e);
        }

        SensorData sensorData;

        while (true){
            sensorData = dataGenerator.generateSensorData();
            String message = sensorData.getJson().toString();
            logger.info(String.format("New Sensor Value: %s", sensorData.getValue()));
            logger.fine("Raw Sensor Data: " + message);

            try {
                mqttClient.sendData(message);
            } catch (MqttException e) {
                logger.warning("Could not send Sensor Data to Server!");
                logger.warning(e.getMessage());
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.warning(e.getMessage());
                break;
            }
        }
    }
}
