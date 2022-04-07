package com.jx.springCloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author LDW
 * @date 2022/3/25 16:59
 */
@Configuration
//下面的注解也可以写在主启动类上
@LoadBalancerClient(name = "CLOUD-PAYMENT-SERVICE", configuration = {MyLoadBalancerConfig.class})
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced //用于开启负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
