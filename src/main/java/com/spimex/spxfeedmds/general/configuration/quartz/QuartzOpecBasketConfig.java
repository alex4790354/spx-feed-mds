package com.spimex.spxfeedmds.general.configuration.quartz;

import com.spimex.spxfeedmds.general.constant.IdentityConstant;
import com.spimex.spxfeedmds.general.constant.OpecBasketConstant;
import com.spimex.spxfeedmds.service.job.OpecLoadJob;
import lombok.RequiredArgsConstructor;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QuartzOpecBasketConfig {

    private final SchedulerProperties schedulerProperties;

    @Bean
    public JobDetail createJobDetailOpecBasket() {
        return JobBuilder.newJob(OpecLoadJob.class)
                .withIdentity(IdentityConstant.JOB_OPEC_ARCHIVES_IDENTITY.getIdentity(), schedulerProperties.getSchedulers().get(OpecBasketConstant.DATA_NAME_OPEC.getSid()).permanentJobsGroupName)
                .storeDurably()
                .requestRecovery(true)
                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "schedulers.opec.enable", havingValue = "true")
    public Trigger showTimeTriggerOpecBasket() {
        return TriggerBuilder.newTrigger()
                .forJob(createJobDetailOpecBasket())
                .withIdentity(IdentityConstant.JOB_OPEC_ARCHIVES_IDENTITY.getIdentity(), schedulerProperties.getSchedulers().get(OpecBasketConstant.DATA_NAME_OPEC.getSid()).permanentJobsGroupName)
                .withSchedule(CronScheduleBuilder.cronSchedule(schedulerProperties.getSchedulers().get(OpecBasketConstant.DATA_NAME_OPEC.getSid()).showTimeJobCron))
                .build();
    }
}
