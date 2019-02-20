package com.happn.poi.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String txt) {
        super("invalid params : " + txt);
    }
}
