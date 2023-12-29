package com.spimex.spxfeedmds.general.dto;

public record ErrorDto(String error, String message) {

    public static ErrorDto of(String error, String message) {
        return new ErrorDto(error, message);
    }
}
