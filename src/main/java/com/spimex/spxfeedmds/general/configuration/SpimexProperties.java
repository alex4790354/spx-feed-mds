package com.spimex.spxfeedmds.general.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "spimex")
public class SpimexProperties {

    private static List<String> minec;

    public List<String> getMinec() {
        return minec;
    }

    public void setMinec(List<String> minec) {
        this.minec = minec;
    }


}
