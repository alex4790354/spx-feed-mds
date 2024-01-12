package com.spimex.spxfeedmds.general.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@Data
@ConfigurationProperties(prefix = "spimex")
public class SpimexProperties {

    private List<String> minec;

    private List<String> cbrf;

}
