package com.example.JavaProject.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NO_CONTENT)
public class EmptyListException extends RuntimeException {
    public EmptyListException(String message) {super(message);}
}
