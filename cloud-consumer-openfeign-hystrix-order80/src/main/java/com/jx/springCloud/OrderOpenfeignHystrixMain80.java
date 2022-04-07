package com.jx.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author LDW
 * @date 2022/4/3 17:17
 */

@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderOpenfeignHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenfeignHystrixMain80.class, args);
    }
}
