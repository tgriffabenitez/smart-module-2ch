package com.tmg.tempsensor.daos;

import com.tmg.tempsensor.models.tables.SensorInformation;
import com.tmg.tempsensor.models.queries.SensorQueries;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class SensorDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SensorDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<SensorInformation> getSensorInformation(Map<String, Object> filters) {
        var query = new StringBuilder(SensorQueries.GET_SENSORS);
        var params = new MapSqlParameterSource();

        filters.forEach((key, value) -> {
            query.append(" AND ").append(key).append(" = :").append(key);
            params.addValue(key, value);
        });

        return namedParameterJdbcTemplate.query(query.toString(), params, this::mapSensor);
    }

    public void save(SensorInformation sensorInformation) {
        var params = new MapSqlParameterSource();
        params.addValue("tz", LocalDateTime.now());
        params.addValue("device_sn", sensorInformation.getDeviceSN());
        params.addValue("command", sensorInformation.getCommand());
        params.addValue("temperature", sensorInformation.getTemperature());
        params.addValue("humidity", sensorInformation.getHumidity());
        namedParameterJdbcTemplate.update(SensorQueries.INSERT_SENSOR, params);
        sensorInformation.setTz(LocalDateTime.now());
    }

    private SensorInformation mapSensor(ResultSet rs, int intRow) throws SQLException {
        var sensorInformation = new SensorInformation();
        sensorInformation.setTz(rs.getTimestamp("tz").toLocalDateTime());
        sensorInformation.setDeviceSN(rs.getString("device_sn"));
        sensorInformation.setCommand(rs.getString("command"));
        sensorInformation.setTemperature(rs.getDouble("temperature"));
        sensorInformation.setHumidity(rs.getDouble("humidity"));
        return sensorInformation;
    }
}
