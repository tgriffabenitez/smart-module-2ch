package com.tmg.tempsensor.helpers;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class FieldValidator {

    private FieldValidator() {}

    public static Set<String> validateFields(Map<String, Object> filters, Class<?> dtoClass) {
        Set<String> validFields = Arrays.stream(dtoClass.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toSet());

        return filters.keySet().stream()
                .filter(field -> !validFields.contains(field))
                .collect(Collectors.toSet());
    }
}
