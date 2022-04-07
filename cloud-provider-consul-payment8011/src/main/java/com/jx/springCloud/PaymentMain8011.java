package com.jx.springCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LDW
 * @date 2022/3/30 16:38
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8011 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8011.class, args);
    }
}
