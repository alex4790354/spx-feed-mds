package com.spimex.spxfeedmds.service.mds;

import com.spimex.spxfeedmds.general.constant.MdsMessageConstant;
import com.spimex.spxfeedmds.general.constant.MnecSidPostfix;
import com.spimex.spxfeedmds.general.constant.OpecSidPostfix;
import com.spimex.spxfeedmds.general.dto.FeedContributeRequest;
import com.spimex.spxfeedmds.general.dto.FeedResponse;
import com.spimex.spxfeedmds.general.util.SidUtil;
import com.spimex.spxfeedmds.repository.FeedMdsRepository;
import com.spimex.spxfeedmds.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedContributeService {

    private final ApplicationContext applicationContext;
    private final UserRoleService userRoleService;
    private final FeedMdsRepository repository;
    private final SidUtil sidUtil;
    private AbstractFeedService abstractOpecFeedService;
    private AbstractFeedService abstractMnecFeedService;


    public List<FeedResponse> addFeedValues(List<FeedContributeRequest> requests, Jwt jwt) {
        userRoleService.checkUserInternalRole(jwt);
        List<FeedResponse> responses = new LinkedList<>();

        requests.forEach(requestDto -> {
            boolean isLoaded = false;
            abstractOpecFeedService = chooseOpecService(requestDto.getSid());
            abstractMnecFeedService = chooseMnecService(requestDto.getSid());
            if (abstractOpecFeedService != null) {
                isLoaded = true;
                FeedResponse status = abstractOpecFeedService.addValues(requestDto);
                responses.add(status);
            }
            if (abstractMnecFeedService != null) {
                isLoaded = true;
                FeedResponse status = abstractMnecFeedService.addValues(requestDto);
                responses.add(status);
            }

            if (!isLoaded) {
                log.warn("Not found service by 'SID' --> {}", requestDto.getSid());
                responses.add(new FeedResponse(
                        requestDto.getSid(),
                        HttpStatus.NOT_FOUND.name(),
                        MdsMessageConstant.MSG_SID_NOT_FOUND));
            }
        });
        return responses;
    }

    public List<FeedResponse> addFeedValues(List<FeedContributeRequest> requests) {

        List<FeedResponse> responses = new LinkedList<>();

        requests.forEach(requestDto -> {
            boolean isLoaded = false;
            abstractOpecFeedService = chooseOpecService(requestDto.getSid());
            abstractMnecFeedService = chooseMnecService(requestDto.getSid());
            if (abstractOpecFeedService != null) {
                isLoaded = true;
                FeedResponse status = abstractOpecFeedService.addValues(requestDto);
                responses.add(status);
            }
            if (abstractMnecFeedService != null) {
                isLoaded = true;
                FeedResponse status = abstractMnecFeedService.addValues(requestDto);
                responses.add(status);
            }

            if (!isLoaded) {
                log.warn("Not found service by 'SID' --> {}", requestDto.getSid());
                responses.add(new FeedResponse(
                        requestDto.getSid(),
                        HttpStatus.NOT_FOUND.name(),
                        MdsMessageConstant.MSG_SID_NOT_FOUND));
            }
        });
        return responses;
    }

    private AbstractFeedService chooseOpecService(String sid) {
        if (sidUtil.isCbrfSidPostfix(sid)) {
            log.debug("The service of the Central Bank of the Russian Federation has been selected...");
            return new CentralBankRFService(repository);
        } else if(sid != null &&
                sid.endsWith(OpecSidPostfix.OPEC_SID) &&
                !sid.endsWith(OpecSidPostfix.OPEC_SID_NOT_REQUIRED)) {
            log.debug("The service of the OPEC has been selected...");
            return new OpecRatesStaticService(repository);
        } else return null;
    }

    private AbstractFeedService chooseMnecService(String sid) {

        if (sid == null) {
            return null;
        } else if (sid.endsWith(MnecSidPostfix.MNEC_SID)) {
            log.debug("The service of the Ministry of Economic Development of Russia has been selected...");
            return new MnecService(repository, sidUtil);
            //return applicationContext.getBean("MnecService", );
        }
        else return null;
    }

}
