package com.spimex.spxfeedmds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpxFeedMdsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpxFeedMdsApplication.class, args);
    }
}
