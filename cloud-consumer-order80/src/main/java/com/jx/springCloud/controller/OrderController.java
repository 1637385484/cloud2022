package com.jx.springCloud.controller;

import com.jx.springCloud.entities.CommonResult;
import com.jx.springCloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author LDW
 * @date 2022/3/25 16:54
 */
@RestController
@Slf4j
public class OrderController {

//    private static final String PAYMENT_URL = "http://localhost:8001";

    /*
        配置了eureka后，消费者从eureka中寻找服务端，eureka中注册了两个服务客户端，名字都叫做CLOUD-PAYMENT-SERVICE
        此时发送请求http://CLOUD-PAYMENT-SERVICE/** 还不知道是由哪个服务端来处理该请求。
        所以要开启RestTemplate的负载均衡功能
     */

    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    private final RestTemplate restTemplate;

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/get/{id}")
    public CommonResult getPayment2(@PathVariable("id") long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info(entity.getStatusCode() + "\t");
            return entity.getBody();
        } else {
            return new CommonResult(499, "操作失败");
        }
    }
}
