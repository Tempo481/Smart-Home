package de.h_da.verteiltesysteme.zentrale.sensor;

public class SequenceNumber {

    public static SequenceNumber instance;
    private long lastSequenceNumber;

    private SequenceNumber(){
        lastSequenceNumber = -1;
    }

    public static SequenceNumber getInstance(){
        if(instance == null){
            instance = new SequenceNumber();
        }

        return instance;
    }

    public long getLastSequenceNumber() {
        return lastSequenceNumber;
    }

    public long incrementSequenceNumber(){
        if(lastSequenceNumber == Long.MAX_VALUE){
            lastSequenceNumber = -1;
        }
        return lastSequenceNumber++;
    }

    public void setLastSequenceNumber(long lastSequenceNumber) {
        this.lastSequenceNumber = lastSequenceNumber;
    }
}
