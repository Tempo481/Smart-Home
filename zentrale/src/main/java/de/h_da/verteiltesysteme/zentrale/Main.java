package de.h_da.verteiltesysteme.zentrale;

import de.h_da.verteiltesysteme.zentrale.thrift.ThriftServer;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {

    public static void main(String[] args) {

        String serverIp = System.getenv("MQTT_IP");
        int serverPort = Integer.parseInt(System.getenv("MQTT_PORT"));



        try {
            MQTTClient mqttClient = new MQTTClient(serverIp, serverPort);
            mqttClient.connect();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

        TCPServer tcpServer = new TCPServer();
        tcpServer.start();

        ThriftServer thriftServer = new ThriftServer();
        thriftServer.start();
    }
}
