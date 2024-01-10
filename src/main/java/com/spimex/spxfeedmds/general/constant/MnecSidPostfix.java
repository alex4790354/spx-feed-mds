package com.spimex.spxfeedmds.general.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MnecSidPostfix {

    URALSMA_MNEC("URALSMA-MNEC"),
    URALSPA_MNEC("URALSPA-MNEC"),
    NSDTDPA_MNEC("NSDTDPA-MNEC"),
    CRUDEEXP_MNEC("CRUDEEXP-MNEC"),
    FUELOEXP_MNEC("FUELOEXP-MNEC");

    public final String sid;

    public static boolean fromMnecPostfixValueIsEquals(String sid) {
        for (MnecSidPostfix sidPostfix : MnecSidPostfix.values()) {
            if (sid != null && sid.endsWith(sidPostfix.sid)) {
                return true;
            }
        }
        return false;
    }
}
