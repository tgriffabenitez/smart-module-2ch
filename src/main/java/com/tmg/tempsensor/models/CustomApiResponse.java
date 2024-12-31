package com.tmg.tempsensor.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomApiResponse {
    private Boolean status;
    private String message;
    private LocalDateTime timeStamp = LocalDateTime.now();
    private Object data;

    public CustomApiResponse(Boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
