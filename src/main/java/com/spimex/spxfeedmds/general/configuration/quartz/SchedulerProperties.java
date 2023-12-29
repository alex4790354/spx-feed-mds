package com.spimex.spxfeedmds.general.configuration.quartz;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties
@RequiredArgsConstructor
@EnableConfigurationProperties
public class SchedulerProperties {

    private final Map<String, OpecData> schedulers = new HashMap<>();

    @Data
    public static class OpecData {
        String permanentJobsGroupName;
        String showTimeJobCron;
    }
}
