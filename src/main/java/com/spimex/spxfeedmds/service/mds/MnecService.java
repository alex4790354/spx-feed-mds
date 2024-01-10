package com.spimex.spxfeedmds.service.mds;

import com.spimex.spxfeedmds.general.constant.MdsMessageConstant;
import com.spimex.spxfeedmds.general.constant.OpecBasketConstant;
import com.spimex.spxfeedmds.general.dto.FeedContributeRequest;
import com.spimex.spxfeedmds.general.dto.FeedResponse;
import com.spimex.spxfeedmds.repository.FeedMdsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MnecService extends AbstractFeedService {

    private final FeedMdsRepository repository;

    @Value("${spimex.teststring}")
    private String spimexTestring;

    @Override
    protected FeedResponse addValues(FeedContributeRequest requests) {

        try {
            repository.addMinecValues(requests);
            System.out.println("spimexTestring: " + spimexTestring);
            log.info("successfully loaded MNEC request with 'SID': [{}]\nWith data: {}", requests.getSid(), requests);
        } catch (Exception e) {
            log.error("""
                            SID: {}
                            Error: {}
                            Message: {}""",
                    OpecBasketConstant.SID_ORBCO_OPEC.getSid(),
                    MdsMessageConstant.UNEXPECTED_ERROR,
                    e.getMessage());
            return new FeedResponse(
                    MdsMessageConstant.UNEXPECTED_ERROR,
                    String.format(MdsMessageConstant.MSG_COMMON_ERROR_MESSAGE, e.getMessage()));
        }
        return new FeedResponse(requests.getSid(), HttpStatus.OK.name());

    }
}
