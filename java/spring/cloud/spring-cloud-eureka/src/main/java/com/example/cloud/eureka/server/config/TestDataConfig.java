package com.example.cloud.eureka.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "test.data")
public class TestDataConfig {
    private Map<String, String> map;

    @PostConstruct
    public void init(){
        System.out.println("---------init------------");
        System.out.println(map);
    }
}
