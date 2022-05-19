package com.abn.amro.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/** Handling Custom Exceptions */

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<String> handleExceptions(RecordNotFoundException exception) {
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(RecordNotCreatedException.class)
    public ResponseEntity<String> handleExceptions(RecordNotCreatedException exception) {
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return entity;
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}