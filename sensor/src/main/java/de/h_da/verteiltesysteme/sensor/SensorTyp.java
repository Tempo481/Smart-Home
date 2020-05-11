package de.h_da.verteiltesysteme.sensor;

public enum SensorTyp {
    Temperatur("Temperatur"),
    Helligkeit("Helligkeit"),
    Niederschlag("Niederschlag"),
    Wind("Wind");

    private String typ;

    SensorTyp(String typ){
        this.typ = typ;
    }

    public String getTyp(){
        return typ;
    }

}
