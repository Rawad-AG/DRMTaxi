package dev.taxiapp.taxi_app.Exceptions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadInputsException extends RuntimeException{
    public BadInputsException(String message){
        super(message);
    }
    
}
