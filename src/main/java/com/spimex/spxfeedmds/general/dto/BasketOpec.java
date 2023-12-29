package com.spimex.spxfeedmds.general.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class BasketOpec {

    private String sid;
    private Date data;
    private BigDecimal val;
}
