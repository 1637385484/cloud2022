package com.jx.springCloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LDW
 * @date 2022/4/1 19:53
 * <p>
 * feign的日志bean注册,注册后在yml文件中开启日志的级别
 */
@Configuration
public class FooConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}