package com.spimex.spxfeedmds.general.exception;

import lombok.Getter;

public class MdsFeedException extends RuntimeException {

    @Getter
    private final String error;
    private final String message;

    public MdsFeedException(String error, String message) {
        this.error = error;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MarketRequestException{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
