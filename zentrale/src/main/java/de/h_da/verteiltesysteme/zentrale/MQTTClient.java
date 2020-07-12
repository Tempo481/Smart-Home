package de.h_da.verteiltesysteme.zentrale;

import de.h_da.verteiltesysteme.zentrale.sensor.Brightness;
import de.h_da.verteiltesysteme.zentrale.sensor.Rainfall;
import de.h_da.verteiltesysteme.zentrale.sensor.Sensor;
import de.h_da.verteiltesysteme.zentrale.sensor.SensorData;
import de.h_da.verteiltesysteme.zentrale.sensor.SequenceNumber;
import de.h_da.verteiltesysteme.zentrale.sensor.Temperature;
import de.h_da.verteiltesysteme.zentrale.sensor.Wind;
import de.h_da.verteiltesysteme.zentrale.thrift.SensorThrift;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.UUID;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

public class MQTTClient implements SensorData {
    private static final String TOPIC = "vs_sensoren";

    private static int QUEUE_LEN = 100;

    private IMqttClient mqttClient;

    public MQTTClient(String hostname, int port) throws MqttException {
        String publisherId = UUID.randomUUID().toString();
        mqttClient = new MqttClient(String.format("tcp://%s:%s", hostname, port),publisherId);
    }

    public void connect() throws MqttException {
        populateSequenceNumber();
        System.out.println("Connecting to MQTT Server...");

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
        long seqNumer = SequenceNumber.getInstance().incrementSequenceNumber();

        System.out.println(timestampLong + " " + name + " " + sensor_type + " " + value + " seq#: " + seqNumer);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timestampLong),
            TimeZone.getDefault().toZoneId());

        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        if(QUEUE_LEN <= SENSOR_ARRAY_LIST.size()){
            SENSOR_ARRAY_LIST.remove(0);
        }


        Sensor sensor = new Sensor(sensor_type, name, value, timestampLong, seqNumer);
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

    public void populateSequenceNumber(){

        String[] ips = new String[]{System.getenv("ANBIETER_A_IP"),System.getenv("ANBIETER_B_IP"),System.getenv("ANBIETER_C_IP")};
        Integer[] ports = new Integer[]{Integer.parseInt(System.getenv("ANBIETER_A_PORT")),Integer.parseInt(System.getenv("ANBIETER_B_PORT")),Integer.parseInt(System.getenv("ANBIETER_C_PORT"))};
        System.out.println(String.format("Requesting Sequence Number from %s Databases...", ips.length));
        long seqNumber = SequenceNumber.getInstance().getLastSequenceNumber();

        for(int i = 0; i < ips.length; i++){
            try{
                System.out.println(String.format("Requesting Data from Anieter #%s @ %s:%s ...", i, ips[i],ports[i]));
                TSocket socket = new TSocket(ips[i], ports[i]);
                socket.open();
                TProtocol protocol = new TBinaryProtocol(socket);
                SensorThrift.Client client = new SensorThrift.Client(protocol);

                long dbSequenceNumber = client.getLastSequenceNumber();
                System.out.println(String.format("Sequence Number #%s of %s is %s", i, ips[i], dbSequenceNumber));

                if(dbSequenceNumber > seqNumber){
                    seqNumber = dbSequenceNumber;
                }
            } catch (TException e) {
                e.printStackTrace();
                System.out.println(String.format("Requesting Data from other Anieter #%s @ %s:%s failed because of:", i, ips[i], ports[i]));
            }
        }

        if(seqNumber == -1){
            System.out.println("Cannot determine Sequence Number from Databases, restarting Service");
            System.exit(-1);
            throw new RuntimeException("System crashed!");
        }

        seqNumber++;//Increment by 1

        SequenceNumber.getInstance().setLastSequenceNumber(seqNumber);
        System.out.println(String.format("Continuing with Sequence Number %s", seqNumber));
    }


}