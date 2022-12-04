package com.example.sweetgift.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Provided properties are invalid")
public class InvalidPropertiesException extends RuntimeException{
    public InvalidPropertiesException(){
        super("Provided properties are invalid");
    }

    public InvalidPropertiesException(String cause){
        super(cause);
    }
}
