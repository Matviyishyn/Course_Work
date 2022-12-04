package com.example.sweetgift.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such object")
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("Object not found");
    }

    public NotFoundException(String cause){
        super(cause);
    }
}
