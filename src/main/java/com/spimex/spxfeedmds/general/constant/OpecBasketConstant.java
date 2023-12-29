package com.spimex.spxfeedmds.general.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OpecBasketConstant {

    SID_ORBCO_OPEC("ORBCO-OPEC"),
    DATA_NAME_OPEC("opec");

    private final String sid;
}
