package de.h_da.verteiltesysteme.sensor;

import java.util.Random;

public class DataGenerator {

    private String sensorName;
    private SensorTyp sensorTyp;
    private Random random;

    public DataGenerator(String sensorName, SensorTyp sensorTyp){
        this.sensorName = sensorName;
        this.sensorTyp = sensorTyp;
        random = new Random();
    }

    /**
     * Returns random float between min and max
     * @param min min value
     * @param max max value
     * @return random number
     */
    private float getFakeValue(float min, float max){
        return random.nextFloat() * (max - min) + min;
    }

    public SensorData generateSensorData(){
        float value;

        switch (sensorTyp){
            case Temperatur:{
                value = getFakeValue(0, 30);
                break;
            }case Niederschlag:{
                value = getFakeValue(0, 100);
                break;
            }case Helligkeit:{
                value = getFakeValue(0, 1);
                break;
            }case Wind:{
                value = getFakeValue(0,200);
                break;
            } default:{
                value = getFakeValue(0, 50);
                break;
            }
        }

        return new SensorData(sensorName, sensorTyp, value);
    }
}
