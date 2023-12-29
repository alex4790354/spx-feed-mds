package com.spimex.spxfeedmds.service.mds;

import com.spimex.spxfeedmds.controller.feing.OpecBasketFeignClient;
import com.spimex.spxfeedmds.general.constant.MdsMessageConstant;
import com.spimex.spxfeedmds.general.constant.OpecBasketConstant;
import com.spimex.spxfeedmds.general.dto.BasketOpec;
import com.spimex.spxfeedmds.general.dto.FeedResponse;
import com.spimex.spxfeedmds.repository.FeedMdsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpecBasketService {

    private final FeedMdsRepository repository;
    private final OpecBasketFeignClient feignClient;

    public FeedResponse getOpecArchivesJob() {
        List<BasketOpec> archives;
        try {
            archives = feignClient.callOpec().stream()
                    .peek(dto -> dto.setSid(OpecBasketConstant.SID_ORBCO_OPEC.getSid()))
                    .toList();
            repository.addAllOpecBasket(archives);
            log.info("Load basketDayArchives.xml by 'SID': [{}]", OpecBasketConstant.SID_ORBCO_OPEC.getSid());
            repository.addOpecStaticJobConstant();
            log.info("Load Static Job constants by 'SID': [{}]", OpecBasketConstant.SID_ORBCO_OPEC.getSid());
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
        return new FeedResponse(OpecBasketConstant.SID_ORBCO_OPEC.getSid(), HttpStatus.OK.name());
    }
}
