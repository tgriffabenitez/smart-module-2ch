package com.tmg.tempsensor.models.tables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorInformation {
    private LocalDateTime tz;
    private String deviceSN;
    private String command;
    private Double temperature;
    private Double humidity;
}
