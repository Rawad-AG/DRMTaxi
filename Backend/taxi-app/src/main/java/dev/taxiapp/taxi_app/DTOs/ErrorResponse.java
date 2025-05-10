package dev.taxiapp.taxi_app.DTOs;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String status = "fail";
    private final String message;
    private final ArrayList<String> errors = new ArrayList<>();
    private final HttpStatus statusCode;
    private final ZonedDateTime timestamp;

    public ErrorResponse(String message) {
        this.message = message;
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        this.timestamp = ZonedDateTime.now();
    }

    public ErrorResponse(HttpStatus statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = ZonedDateTime.now();
    }  

    public ErrorResponse(HttpStatus statusCode, ArrayList<String> errors){
        this.message = "error";
        this.statusCode = statusCode;
        this.errors.addAll(errors);
        this.timestamp = ZonedDateTime.now();
    }
}
