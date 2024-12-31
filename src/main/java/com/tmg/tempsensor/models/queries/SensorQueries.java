package com.tmg.tempsensor.models.queries;

import lombok.Data;

@Data
public class SensorQueries {
    public static final String GET_SENSORS = "SELECT * FROM sensor WHERE 1=1";
    public static final String GET_SENSOR_BY_ID = "SELECT * FROM sensor WHERE device_sn = :deviceSN";
    public static final String INSERT_SENSOR = "INSERT INTO sensor (tz, device_sn, command, temperature, humidity) VALUES (:tz, :device_sn, :command, :temperature, :humidity)";
    public static final String DELETE_SENSOR = "SELETE FROM sensor WHERE device_sn = :deviceSN";

    private SensorQueries() {}
}
