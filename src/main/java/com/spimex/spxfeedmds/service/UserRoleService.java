package com.spimex.spxfeedmds.service;

import com.spimex.spxfeedmds.general.constant.MdsMessageConstant;
import com.spimex.spxfeedmds.general.constant.SecurityConstants;
import com.spimex.spxfeedmds.general.exception.MdsPermissionCheckException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRoleService {

    @Value("${MdsData.security.roles.internal}")
    private String userInternal;

    public void checkUserInternalRole(Jwt jwt) {
        Map<String, List<String>> realmAccess = jwt.getClaim(SecurityConstants.REALM_ACCESS);
        if (MapUtils.isNotEmpty(realmAccess) && !realmAccess.get(SecurityConstants.ROLES).contains(userInternal) || MapUtils.isEmpty(realmAccess)) {
            log.error("Error: {} \nMessage: {}", MdsMessageConstant.PERMISSION_ERROR, MdsMessageConstant.MSG_ROLES_MDS_ERROR_MESSAGE);
            throw new MdsPermissionCheckException(MdsMessageConstant.PERMISSION_ERROR, MdsMessageConstant.MSG_ROLES_MDS_ERROR_MESSAGE);
        }
    }
}
