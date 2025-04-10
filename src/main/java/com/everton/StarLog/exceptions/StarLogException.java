package com.everton.StarLog.exceptions;

import lombok.Getter;

@Getter
public class StarLogException extends RuntimeException{

    private final Integer status;

    public StarLogException(final String message, final Integer status){
        super(message);
        this.status = status;
    }
}
