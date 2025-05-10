package dev.taxiapp.taxi_app.Exceptions.handlers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import dev.taxiapp.taxi_app.Configs.EnvironmentChecker;
import dev.taxiapp.taxi_app.DTOs.ErrorResponse;
import dev.taxiapp.taxi_app.Utils.Messager;
import io.jsonwebtoken.JwtException;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final EnvironmentChecker env;


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleRouteNotFoundException(NoResourceFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND, Messager.routeNotFound("")));
    }
    
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException e){
        String message = env.isDevelopment() ? e.getMessage() : Messager.invalidToken();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(HttpStatus.UNAUTHORIZED, message));    
    }
    
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()));    
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
        ArrayList<String> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage()))
        .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, errors));
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUncaughtExceptions(Exception e) {
        log.error("error: ", e);
        HttpStatus code = getStatusCode(e);
        String message = env.isDevelopment() ? e.getMessage() : "An unexpected error occurred";

        return ResponseEntity.status(code).body(new ErrorResponse(code, message));
    }

    private HttpStatus getStatusCode(Exception e) {
        ResponseStatus responseStatus = e.getClass().getAnnotation(ResponseStatus.class);
        if (responseStatus != null) {
            return responseStatus.value();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
