package de.h_da.verteiltesysteme.sensor;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Simple UDP Send Client
 */
public class MQTTClient {
    private IMqttClient mqttClient;
    private static final String TOPIC = "vs_sensoren";

    /**
     * UDPClient constructor
     * @param hostname hostname of the server
     * @param port port of the server
     * @throws MqttException error while connecting to the mqtt server
     */
    public MQTTClient(String hostname, int port) throws MqttException {
        mqttClient = new MqttClient(String.format("tcp://%s:%s", hostname, port), UUID.randomUUID().toString());

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true); //Auto Reconnect
        options.setCleanSession(true);
        options.setConnectionTimeout(10);

        //Connect to the server
        mqttClient.connect(options);
    }

    /**
     * Sends data to the given server
     * @param data data to send
     * @throws MqttException error on transmission
     */
    public void sendData(String data) throws MqttException {
        MqttMessage message = new MqttMessage(data.getBytes());

        message.setQos(1);
        message.setRetained(true);
        mqttClient.publish(TOPIC, message);
    }
}
