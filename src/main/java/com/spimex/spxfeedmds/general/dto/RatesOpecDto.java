package com.spimex.spxfeedmds.general.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;
import java.util.stream.Stream;

@Data
public class RatesOpecDto {

    @JsonProperty("SID")
    private String sid;
    @JsonProperty("EFFECTIVE_DATE")
    private Date effectiveDate;
    @JsonProperty("UPDATE_DATE")
    private Date updateDate;
    @JsonProperty("LAST")
    private BigDecimal last;

    public boolean necessaryDataHasBeenEntered() {
        return Stream.of(
                        this.sid,
                        this.effectiveDate,
                        this.updateDate,
                        this.last)
                .allMatch(Objects::nonNull);
    }
}
