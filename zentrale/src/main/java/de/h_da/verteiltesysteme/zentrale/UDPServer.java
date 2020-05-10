package de.h_da.verteiltesysteme.zentrale;

import java.time.Instant;
import java.util.TimeZone;
import org.json.JSONObject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UDPServer extends Thread implements SensorData {

    @Override
    public void run() {
        try {
            listenOnUDPSocketAndReceiveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * UDP Pakete werden empfangen und der Inhalt
    * als String zur Funktion parseJSON übergeben.
    */
    public static void listenOnUDPSocketAndReceiveData() throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(50000);
        DatagramPacket receivePacket = null;
        System.out.println("listening on UDP Socket ...\n");

        while (true) {
            byte[] receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String message = new String(receivePacket.getData());
            message = message.substring(0, message.indexOf('\u0000'));
            parseJSON(message);
            InetAddress inetAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            System.out.println("[" + inetAddress + "][" + port + "] " + message);
//            System.out.println("RECEIVED from " + inetAddress + " Port: " + port + ": " + message);
        }
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

        switch (sensor_type) {
            case "Temperatur": {
                Temperature temperature = new Temperature(value, timestamp, sensor_type, name);
                TEMPERATURE_SENSOR_DATA.add(temperature);
                break;
            }
            case "Brightness": {
                Brightness brightness = new Brightness(timestamp, value, sensor_type, name);
                BRIGHTNESS_SENSOR_DATA.add(brightness);
                break;
            }
            case "WindSpeed": {
                WindSpeed windSpeed = new WindSpeed(value, timestamp, sensor_type, name);
                WINDSPEED_SENSOR_DATA.add(windSpeed);
                break;
            }
        }
    }
}