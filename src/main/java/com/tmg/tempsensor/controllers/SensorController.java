package com.tmg.tempsensor.controllers;

import com.tmg.tempsensor.helpers.FieldValidator;
import com.tmg.tempsensor.models.CustomApiResponse;
import com.tmg.tempsensor.models.tables.SensorInformation;
import com.tmg.tempsensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("api/sensors")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public ResponseEntity<CustomApiResponse> buscar(@RequestParam Map<String, Object> filters) {
        Set<String> invalidFields = FieldValidator.validateFields(filters, SensorInformation.class);

        if (!invalidFields.isEmpty()) {
            String invalidParams = String.join(", ", invalidFields);
            CustomApiResponse errorResponse = new CustomApiResponse(
                    false,
                    "Invalid filter params: " + invalidParams,
                    null
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }

        Object data = sensorService.fetchSensorInformation(filters);
        CustomApiResponse successResponse = new CustomApiResponse(
                true,
                "Data fetched correctly",
                data
        );
        return ResponseEntity.ok().body(successResponse);
    }

}
