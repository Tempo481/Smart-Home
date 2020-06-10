package de.h_da.verteiltesysteme.zentrale.db;

import java.net.UnknownHostException;

public class DatabaseTester {
    //TODO: remove this small test class
    public void main(){
        Database database = Database.getInstance();
        if(!database.isConnected()){
            try {
                database.connect();
            } catch (UnknownHostException e) {
                System.out.println("There was an error connecting to the database...:");
                e.printStackTrace();
            }
        }

        long timestamp = System.currentTimeMillis();
        float value = 0.1f;

        for(int i = 0; i < 10; i++){
            database.insertSensorData(new SensorData("Temperatur", "Terasse", timestamp, value));
            timestamp -= 1000;
            value += 0.2f;
        }

        for(int i = 0; i < 100; i++){
            database.insertSensorData(new SensorData("Temperatur", "Wohnzimmer", timestamp, value));
            timestamp -= 1000;
            value += 0.2f;
        }

        System.out.println("Last 40 Entries:");
        for(SensorData data: database.getLastXEntries(40)){
            System.out.println(data);
        }

        timestamp = System.currentTimeMillis();

        System.out.println(String.format("Entries after %s", timestamp - 60000));
        for(SensorData data: database.getSensorDataAfterTimestamp(timestamp - 60000)){
            System.out.println(data);
        }

        System.out.println(String.format("Entries after now %s", timestamp));
        for(SensorData data: database.getSensorDataAfterTimestamp(timestamp)){
            System.out.println(data);
        }


    }

}
