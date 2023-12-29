package com.spimex.spxfeedmds.general.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CbrfSidPostfix {

    Y0_CBRF("Y0-CBRF"),
    Y1_CBRF("Y1-CBRF"),
    Y2_CBRF("Y2-CBRF"),
    Y3_CBRF("Y3-CBRF");

    public final String sid;

    public static boolean fromCbrfPostfixValueIsEquals(String sid) {
        for (CbrfSidPostfix sidPostfix : CbrfSidPostfix.values()) {
            if (sid != null && sid.endsWith(sidPostfix.sid)) {
                return true;
            }
        }
        return false;
    }
}
