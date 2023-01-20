package com.example.JavaProject.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotValidException extends RuntimeException {
    public NotValidException(String message) {super(message);}
}
