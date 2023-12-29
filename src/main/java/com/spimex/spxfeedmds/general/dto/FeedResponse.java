package com.spimex.spxfeedmds.general.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedResponse {

    private String sid;
    private String status;
    private String error;
    private String message;

    public FeedResponse(String sid, String status) {
        this.sid = sid;
        this.status = status;
    }

    public FeedResponse(String sid, String error, String message) {
        this.sid = sid;
        this.error = error;
        this.message = message;
    }
}
