package com.everton.StarLog.controllers;

import com.everton.StarLog.exceptions.StarLogException;
import com.everton.StarLog.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class Observer {

    @ExceptionHandler(StarLogException.class)
    ResponseEntity<Error> handlerExceptions(StarLogException e){
        Error error = Error.builder()
                .message("Parameter validation error ")
                .status(400)
                .build();

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Error> handlerException(MethodArgumentNotValidException ex){
        List<Error.Fields> fieldsList = new ArrayList<>();
        ex.getFieldErrors().forEach(fieldError -> {
            Error.Fields fields = Error.Fields.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                            .build();
            fieldsList.add(fields);
        });

        Error error = Error.builder()
                .status(400)
                .message("Parameter validation error")
                .build();

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<Error> handlerException(MethodArgumentTypeMismatchException e){

        Error error = Error.builder()
                .message("Parameter validation error")
                .status(400)
                .build();

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Error> HandlerException(Exception e){
        Error erro = Error.builder()
                .status(500)
                .message(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                .build();

        return new ResponseEntity<>(erro,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
