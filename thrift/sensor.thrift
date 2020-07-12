struct SensorDataThrift {
    1: string nameOfSensor,
    2: string typeOfSensor,
    3: i64 timestamp,
	4: i64 sequenceNumber,
    5: double value
}

service SensorThrift {
    void ping(),
    list<SensorDataThrift> getNewestSensorDataList(),
	i64 getLastSequenceNumber(),
	list<SensorDataThrift> getSensorDataListAfter(i64 timestamp, i64 sequenceNumber)
}