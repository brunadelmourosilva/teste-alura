package br.com.alura.school.enroll.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> handleBadRequestException(BadRequestException ex) {
        var error = new StandardError(ex.getMessage(), Date.from(Instant.now()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
