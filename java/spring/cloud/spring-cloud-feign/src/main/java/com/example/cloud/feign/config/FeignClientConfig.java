package com.example.cloud.feign.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignClientConfig {

    /**
     * 默认采用指数退避的形式进行重试 1.5次方
     * @return
     */
    @Bean
    public Retryer retryer(){
        return new Retryer.Default(50, TimeUnit.SECONDS.toMillis(2),6);
    }

    /**
     * 定义feign日志级别 用于程序调试
     * @return
     */
    @Bean
    public Logger.Level feignClientLogLevel(){
        return Logger.Level.FULL;
    }

}
