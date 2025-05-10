package dev.taxiapp.taxi_app.DTOs;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;



@Getter
public class SuccessResponse {
    
    private final String status = "success";
    private final String message;
    private final Object data;
    private final HttpStatus statusCode;
    private final ZonedDateTime timestamp;

    public SuccessResponse(String message) {
        this.data = null;
        this.message = message;
        this.statusCode = HttpStatus.OK;
        this.timestamp = ZonedDateTime.now();
    }

    public SuccessResponse(HttpStatus statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = ZonedDateTime.now();
        this.data = null;
    }

    public SuccessResponse(String message, Object data) {
        this.data = data;
        this.message = message;
        this.statusCode = HttpStatus.OK;
        this.timestamp = ZonedDateTime.now();
    }

    public SuccessResponse(HttpStatus statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
        this.timestamp = ZonedDateTime.now();
    }
}
