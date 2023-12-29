package com.spimex.spxfeedmds.general.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IdentityConstant {

    JOB_OPEC_ARCHIVES_IDENTITY("opecArchivesLoadJob");

    private final String identity;
}
