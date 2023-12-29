package com.spimex.spxfeedmds.service.mds;

import com.spimex.spxfeedmds.general.constant.CbrfSidPostfix;
import com.spimex.spxfeedmds.general.constant.MdsMessageConstant;
import com.spimex.spxfeedmds.general.constant.OpecSidPostfix;
import com.spimex.spxfeedmds.general.dto.FeedContributeRequest;
import com.spimex.spxfeedmds.general.dto.FeedResponse;
import com.spimex.spxfeedmds.repository.FeedMdsRepository;
import com.spimex.spxfeedmds.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedContributeService {

    private final UserRoleService userRoleService;
    private AbstractFeedService abstractFeedService;

    private final FeedMdsRepository repository;

    public List<FeedResponse> addFeedValues(List<FeedContributeRequest> requests, Jwt jwt) {
        userRoleService.checkUserInternalRole(jwt);
        List<FeedResponse> responses = new LinkedList<>();

        requests.forEach(requestDto -> {
            abstractFeedService = chooseService(requestDto.getSid());
            if (abstractFeedService != null) {
                FeedResponse status = abstractFeedService.addValues(requestDto);
                responses.add(status);
            } else {
                log.warn("Not found service by 'SID' --> {}", requestDto.getSid());
                responses.add(new FeedResponse(
                        requestDto.getSid(),
                        HttpStatus.NOT_FOUND.name(),
                        MdsMessageConstant.MSG_SID_NOT_FOUND));
            }
        });
        return responses;
    }

    private AbstractFeedService chooseService(String sid) {
        if (CbrfSidPostfix.fromCbrfPostfixValueIsEquals(sid)) {
            log.debug("The service of the Central Bank of the Russian Federation has been selected...");
            return new CentralBankRFService(repository);
        } else if(sid != null &&
                sid.endsWith(OpecSidPostfix.OPEC_SID) &&
                !sid.endsWith(OpecSidPostfix.OPEC_SID_NOT_REQUIRED)) {
            log.debug("The service of the OPEC has been selected...");
            return new OpecRatesStaticService(repository);
        } else return null;
    }
}
