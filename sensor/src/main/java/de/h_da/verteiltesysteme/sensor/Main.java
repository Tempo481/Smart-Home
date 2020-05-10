package de.h_da.verteiltesysteme.sensor;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.setLevel(Level.ALL);

        logger.info("Sensor is starting...");

        String sensorName = System.getenv("SENSOR_NAME");
        String sensorTypString = System.getenv("SENSOR_TYPE");
        String serverIp = System.getenv("ZENTRALE_IP");
        String serverPortString = System.getenv("ZENTRALE_PORT");

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
        UDPClient udpClient;

        try {
            udpClient = new UDPClient(serverIp, serverPort);
        } catch (UnknownHostException | SocketException e) {
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
                udpClient.sendData(message);
            } catch (IOException e) {
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
