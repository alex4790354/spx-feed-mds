package com.spimex.spxfeedmds.service.mds;

import com.spimex.spxfeedmds.general.constant.MdsMessageConstant;
import com.spimex.spxfeedmds.general.constant.MnecSidPostfix;
import com.spimex.spxfeedmds.general.dto.FeedContributeRequest;
import com.spimex.spxfeedmds.general.dto.FeedResponse;
import com.spimex.spxfeedmds.general.util.SidUtil;
import com.spimex.spxfeedmds.repository.FeedMdsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MnecService extends AbstractFeedService {

    private final FeedMdsRepository repository;
    private final SidUtil sidUtil;

    @Override
    protected FeedResponse addValues(FeedContributeRequest requests) {

        //TODO: delete this:
        System.out.println("!sidUtil.isStringInMinecList(requests.getSid()): " + !sidUtil.isStringInMinecSidList(requests.getSid()));
        System.out.println("requests.getFields().isHaveAllFieldsForInitialLoadingStaticMNEC()" + requests.getFields().isMinecStaticLoad());

        try {
            boolean isLoaded = false;

            if (requests.getFields().isMinecStaticLoad()) {
                isLoaded = true;
                repository.addMinecStaticValues(requests);
                log.info("successfully loaded Minec STATIC request to minec_static with 'SID': [{}]\nWith data: {}", requests.getSid(), requests);
            }
            if (!sidUtil.isStringInMinecSidList(requests.getSid()) && requests.getFields().isMinecForecastLoad()) {
                isLoaded = true;
                repository.addMinecForecastValues(requests);
                log.info("successfully loaded Minec FORECAST request to minec_forecast with 'SID': [{}]\nWith data: {}", requests.getSid(), requests);
            }
            if (sidUtil.isStringInMinecSidList(requests.getSid()) && requests.getFields().isMinecExportLoad()) {
                isLoaded = true;
                repository.addMinecExportValues(requests);
                log.info("successfully loaded Minec EXPORT request to minec_export with 'SID': [{}]\nWith data: {}", requests.getSid(), requests);
            }
            if (!isLoaded) {
                log.info("Minec request was NOT loaded to DB. 'SID': [{}]\nData: {}", requests.getSid(), requests);
            }
        } catch (Exception e) {
            log.error("""
                            SID: {}
                            Error: {}
                            Message: {}""",
                    MnecSidPostfix.MNEC_SID,
                    MdsMessageConstant.UNEXPECTED_ERROR,
                    e.getMessage());
            return new FeedResponse(
                    MdsMessageConstant.UNEXPECTED_ERROR,
                    String.format(MdsMessageConstant.MSG_COMMON_ERROR_MESSAGE, e.getMessage()));
        }
        return new FeedResponse(requests.getSid(), HttpStatus.OK.name());

    }
}
