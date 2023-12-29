package com.spimex.spxfeedmds.general.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class SidFieldsRequest {

    @JsonProperty("CONSENSUS_DATE")
    private Date consensusDate;
    @JsonProperty("INSTR_NAME")
    private String instrName;   //CBRF and OPEC
    @JsonProperty("TENOR")
    private String tenor;
    @JsonProperty("SOURCE")
    private String source;      //CBRF and OPEC
    @JsonProperty("UNIT")
    private String unit;        //CBRF and OPEC
    @JsonProperty("CRNCY")
    private String crncy;       //CBRF and OPEC
    @JsonProperty("F_VAL_AV")
    private BigDecimal valueAv;
    @JsonProperty("F_VAL_MD")
    private BigDecimal valueMd;
    @JsonProperty("F_VAL_MX")
    private BigDecimal valueMx;
    @JsonProperty("F_VAL_MN")
    private BigDecimal valueMn;
    @JsonProperty("F_VAL_A_PERS")
    private BigDecimal valueAPers;

    //OPEC
    @JsonProperty("SID")
    private String sid;
    @JsonProperty("INSTR_NAME_ENG")
    private String instrNameEng;
    @JsonProperty("FREQUENCY")
    private String frequency;
    @JsonProperty("EFFECTIVE_DATE")
    private Date effectiveDate;
    @JsonProperty("UPDATE_DATE")
    private Date updateDate;
    @JsonProperty("LAST")
    private BigDecimal last;
}
