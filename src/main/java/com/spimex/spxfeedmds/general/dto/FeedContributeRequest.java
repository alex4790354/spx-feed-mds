package com.spimex.spxfeedmds.general.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spimex.spxfeedmds.general.dto.request.SidFieldsRequest;
import lombok.Data;

@Data
public class FeedContributeRequest {

    @JsonProperty("SID")
    private String sid;
    private SidFieldsRequest fields;
}
