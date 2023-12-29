package com.spimex.spxfeedmds.general.configuration.quartz;


import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SchedulerConfig {

    @Bean
    public Scheduler scheduler(List<Trigger> triggerList,
                               SchedulerFactoryBean factory) throws SchedulerException {
        factory.setWaitForJobsToCompleteOnShutdown(true);
        Scheduler scheduler = factory.getScheduler();
        rescheduleTriggers(triggerList, scheduler);
        scheduler.start();
        return scheduler;
    }


    public void rescheduleTriggers(List<Trigger> triggers, Scheduler scheduler) throws SchedulerException {
        for (Trigger trigger : triggers) {
            if(!scheduler.checkExists(trigger.getKey())) {
                scheduler.scheduleJob(trigger);
            }else {
                scheduler.rescheduleJob(trigger.getKey(), trigger);
            }
        }
    }
}
