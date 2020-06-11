struct SensorDataThrift {
    1: string nameOfSensor,
    2: string typeOfSensor,
    3: i64 timestamp,
    4: double value
}

service SensorThrift {
    void ping(),
    list<SensorDataThrift> getSensorDataList()
}