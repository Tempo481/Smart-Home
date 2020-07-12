package de.h_da.verteiltesysteme.anbieter.db;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import javax.print.Doc;
import org.bson.Document;

public class Database {
    private static final String DATABASE_NAME = "verteilte_systeme";
    private static final String DATABASE_COLLECTION = "sensor_data";

    private MongoClient client;
    private MongoClientURI clientURI;
    private MongoCollection<Document> dbCollection;

    private static Database instance;


    //Default Constructor
    private Database(String username, String password, String host, int port){
        clientURI = new MongoClientURI(String.format("mongodb://%s:%s@%s:%s", username, password, host, port));
        System.out.println(String.format("Database connection URI: %s", clientURI));
    }

    //Simplefied Constructor
    private Database(){
        this(
            System.getenv("DB_USERNAME"),
            System.getenv("DB_PASSWORD"),
            System.getenv("DB_HOST"),
            Integer.parseInt(System.getenv("DB_PORT"))
        );
    }

    //Instance Access
    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
            instance.connect();
        }

        return instance;
    }

    /**
     * Connects to Database
     */
    private void connect(){
        if(!isConnected()){
            client = new MongoClient(clientURI);
            MongoDatabase database = client.getDatabase(DATABASE_NAME);
            dbCollection = database.getCollection(DATABASE_COLLECTION);
        }
    }

    /**
     * Check if database is connected
     * @return true if connected
     */
    public boolean isConnected(){
        return client != null;
    }

    /**
     * Insert new Sensor Data
     * @param sensorData Data to store to database
     */
    public void insertSensorData(SensorData sensorData){
        dbCollection.insertOne(sensorData.toDatabaseObject());
    }

    public void insertManySensorData(List<SensorData> sensorDataList){
        List<Document> documents = new ArrayList<>();

        for (SensorData sensorData: sensorDataList) {
            documents.add(sensorData.toDatabaseObject());
        }

        if(documents.isEmpty()){
            return;
        }

        dbCollection.insertMany(documents);
    }

    /**
     * Get Sensor Data by time in the past
     * Use timestamp = System.currentTimeMillis() - 1000 * 60 to get Data from the last minute
     * @param timestamp timestamp to search after (after that time)
     * @param sensorName optional Sensor name to filter on, can be null to ignore
     * @param sensorTyp optional Sensor typ to filter on, can be null to ignore
     * @return a list of sensor data
     */
    public List<SensorData> getSensorDataAfterTimestamp(long timestamp, String sensorName, String sensorTyp){
        Document query = new Document();
        query.put(SensorData.FIELD_TIMESTAMP, new BasicDBObject("$gt", timestamp));
        if(sensorName != null){
            query.put(SensorData.FIELD_NAME_OF_SENSOR, sensorName);
        }

        if(sensorTyp != null){
            query.put(SensorData.FIELD_TYPE_OF_SENSOR, sensorTyp);
        }

        MongoCursor<Document> cursor = dbCollection.find(query).iterator();

        List<SensorData> sensorDataList = new ArrayList<>();
        while (cursor.hasNext()){
            sensorDataList.add(new SensorData(cursor.next()));
        }

        return sensorDataList;
    }

    public List<SensorData> getSensorDataAfterTimestamp(long timestamp){
        return getSensorDataAfterTimestamp(timestamp,null, null);
    }

    /**
     * Get Sensor Data by last inserted amount
     * @param limit maximum amount of data
     * @param sensorName optional Sensor name to filter on, can be null to ignore
     * @param sensorTyp optional Sensor typ to filter on, can be null to ignore
     * @return a list of sensor data
     */
    public List<SensorData> getLastXEntries(int limit, String sensorName, String sensorTyp){
        Document query = new Document();
        if(sensorName != null){
            query.put(SensorData.FIELD_NAME_OF_SENSOR, sensorName);
        }

        if(sensorTyp != null){
            query.put(SensorData.FIELD_TYPE_OF_SENSOR, sensorTyp);
        }

        MongoCursor<Document> cursor = dbCollection.find(query).limit(limit).iterator();

        List<SensorData> sensorDataList = new ArrayList<>();
        while (cursor.hasNext()){
            sensorDataList.add(new SensorData(cursor.next()));
        }

        return sensorDataList;
    }

    public List<SensorData> getLastXEntries(int limit){
        return getLastXEntries(limit, null, null);
    }

    public long getLastSequenceNumber(){

        //Get start if the current sequence
        Document query = new Document();
        query.put(SensorData.FIELD_SEQUENC_NUMBER, 0);
        MongoCursor<Document> cursor = dbCollection.find(query)
            .sort(new Document(SensorData.FIELD_TIMESTAMP, -1))
            .limit(1).iterator();

        long sequenceTimestamp = -1;
        if(cursor.hasNext()){
            sequenceTimestamp = cursor.next().getLong(SensorData.FIELD_TIMESTAMP);
        }

        query = new Document(SensorData.FIELD_TIMESTAMP, new Document("$gt", sequenceTimestamp));

        //Get Last Elements in the current sequence
        cursor = dbCollection.find(query).sort(new Document(SensorData.FIELD_SEQUENC_NUMBER, -1)).limit(1).iterator();

        long sequenceNumber = 0;
        if(cursor.hasNext()){
            sequenceNumber = cursor.next().getLong(SensorData.FIELD_SEQUENC_NUMBER);
        }else{
            System.out.println("Warning no Sequence Number was found?!");
        }

        return sequenceNumber;
    }

    public long getLastTimestamp(){
        MongoCursor<Document> cursor = dbCollection.find().sort(new Document(SensorData.FIELD_TIMESTAMP, -1)).limit(1).iterator();

        if(cursor.hasNext()){
            return cursor.next().getLong(SensorData.FIELD_TIMESTAMP);
        }else{
            System.out.println("Warning no Timestamp was found?!");
            return -1;
        }
    }

    public List<SensorData> getEntriesAfter(long sequenceNumber, long timestamp){
        System.out.println(String.format("Database.getEntriesAfter %s %s (local: %s)", sequenceNumber, timestamp, getLastSequenceNumber()));
        Document query = new Document();
        query.put(SensorData.FIELD_TIMESTAMP, new Document("$gte", timestamp));
        query.put(SensorData.FIELD_SEQUENC_NUMBER, new Document("$gt", sequenceNumber));

        MongoCursor<Document> cursor = dbCollection.find(
            query
        ).sort(
            new Document(SensorData.FIELD_SEQUENC_NUMBER, 1)
        ).iterator();

        List<SensorData> sensorDataList = new ArrayList<>();
        while (cursor.hasNext()){
            sensorDataList.add(new SensorData(cursor.next()));
        }

        return sensorDataList;
    }
}
