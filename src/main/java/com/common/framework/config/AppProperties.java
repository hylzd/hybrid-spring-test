package com.common.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Data
@ConfigurationProperties("app.properties")
@Component
public class AppProperties {

    private int timeout;
    private int implicitTime;
    private int explicitTime;

    private String appType;
    private String url;
    private String appiumURL;
    private String appPackage;
    private String deviceName;

}
