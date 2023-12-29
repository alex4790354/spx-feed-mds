package com.spimex.spxfeedmds.service.mds;

import com.spimex.spxfeedmds.general.constant.MdsMessageConstant;
import com.spimex.spxfeedmds.general.constant.OpecBasketConstant;
import com.spimex.spxfeedmds.general.dto.FeedContributeRequest;
import com.spimex.spxfeedmds.general.dto.FeedResponse;
import com.spimex.spxfeedmds.general.dto.RatesOpecDto;
import com.spimex.spxfeedmds.general.dto.StaticOpecDto;
import com.spimex.spxfeedmds.general.mapper.FeedMdsMapper;
import com.spimex.spxfeedmds.repository.FeedMdsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpecRatesStaticService extends AbstractFeedService {

    private final FeedMdsRepository repository;

    @Override
    protected FeedResponse addValues(FeedContributeRequest requests) {
        StaticOpecDto staticOpecDto = FeedMdsMapper.INSTANCE.requestToStaticOpec(requests.getFields());
        staticOpecDto.setSid(requests.getSid());
        log.debug("Opec static DTO: {}", staticOpecDto);
        RatesOpecDto ratesOpecDto = FeedMdsMapper.INSTANCE.requestToRatesOpec(requests.getFields());
        ratesOpecDto.setSid(requests.getSid());
        log.debug("Opec rates DTO: {}", ratesOpecDto);

        if (staticOpecDto.necessaryDataHasBeenEntered()) {
            try {
                repository.addOpecStatic(staticOpecDto);
                log.info("Load OPEC Static by 'SID': [{}]\nWith data: {}", requests.getSid(), staticOpecDto);
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
        } else {
            log.warn("Not found all required fields OPEC Static by 'SID': [{}]\nWith data: {}", requests.getSid(), staticOpecDto);
        }

        if (ratesOpecDto.necessaryDataHasBeenEntered()) {
            try {
                repository.addOpecRates(ratesOpecDto);
                log.info("Load OPEC Rates by 'SID': [{}]\nWith data: {}", requests.getSid(), ratesOpecDto);
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
        } else {
            log.warn("Not found all required fields OPEC Rates by 'SID': [{}]\nWith data: {}", requests.getSid(), ratesOpecDto);
        }
        return new FeedResponse(requests.getSid(), HttpStatus.OK.name());
    }
}
