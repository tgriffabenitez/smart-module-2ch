package com.tmg.tempsensor.services;

import com.tmg.tempsensor.daos.SensorDao;
import com.tmg.tempsensor.models.tables.SensorInformation;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class SensorService {

    private final SensorDao sensorDao;

    public SensorService(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    public List<SensorInformation> fetchSensorInformation(Map<String, Object> filters) {
        return sensorDao.getSensorInformation(filters);
    }

    public void saveSensorInformation(SensorInformation sensorInformation) {
        sensorDao.save(sensorInformation);
    }
}
