package com.jx.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author LDW
 * @date 2022/3/25 16:48
 */
@SpringBootApplication
@EnableEurekaClient
public class orderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(orderMain80.class, args);
    }
}
