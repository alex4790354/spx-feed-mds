package com.spimex.spxfeedmds.general.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Objects;
import java.util.stream.Stream;

@Data
public class StaticOpecDto {

    //OPEC
    @JsonProperty("SID")
    private String sid;
    @JsonProperty("INSTR_NAME")
    private String instrName;
    @JsonProperty("INSTR_NAME_ENG")
    private String instrNameEng;
    @JsonProperty("FREQUENCY")
    private String frequency;
    @JsonProperty("SOURCE")
    private String source;
    @JsonProperty("UNIT")
    private String unit;
    @JsonProperty("CRNCY")
    private String crncy;
    @JsonProperty("PROD_PERM")
    private Integer prodPerm;

    public boolean necessaryDataHasBeenEntered() {
        return Stream.of(
                        this.sid,
                        this.instrName,
                        this.instrNameEng,
                        this.frequency,
                        this.source,
                        this.unit)
                .allMatch(Objects::nonNull);
    }
}
