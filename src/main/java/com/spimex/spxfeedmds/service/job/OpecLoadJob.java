package com.spimex.spxfeedmds.service.job;

import com.spimex.spxfeedmds.general.constant.OpecBasketConstant;
import com.spimex.spxfeedmds.general.dto.FeedResponse;
import com.spimex.spxfeedmds.service.mds.OpecBasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class OpecLoadJob extends QuartzJobBean {

    private final OpecBasketService service;

    @Override
    protected void executeInternal(JobExecutionContext context) {

        Map<String, Object> dataMap = context.getJobDetail().getJobDataMap();
        FeedResponse response =  service.getOpecArchivesJob();

        dataMap.put(OpecBasketConstant.SID_ORBCO_OPEC.getSid(), response.toString());
    }
}
