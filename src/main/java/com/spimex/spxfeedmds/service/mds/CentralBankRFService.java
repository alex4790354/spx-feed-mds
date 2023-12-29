package com.spimex.spxfeedmds.service.mds;

import com.spimex.spxfeedmds.general.constant.MdsMessageConstant;
import com.spimex.spxfeedmds.general.dto.FeedContributeRequest;
import com.spimex.spxfeedmds.general.dto.FeedResponse;
import com.spimex.spxfeedmds.repository.FeedMdsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CentralBankRFService extends AbstractFeedService {

    private final FeedMdsRepository repository;

    @Override
    protected FeedResponse addValues(FeedContributeRequest request) {
        try {
            repository.addFeedCentralBankValues(request);
        } catch (Exception e) {
            log.error("SID: {}\nError: {}\nMessage: {}", request.getSid(),  MdsMessageConstant.UNEXPECTED_ERROR, e.getMessage());
            return new FeedResponse(
                    MdsMessageConstant.UNEXPECTED_ERROR,
                    String.format(MdsMessageConstant.MSG_COMMON_ERROR_MESSAGE, e.getMessage()));
        }
        log.info("Contribute MDS fields by 'SID' --> {}", request.getSid());
        return new FeedResponse(request.getSid(), HttpStatus.OK.name());
    }
}
