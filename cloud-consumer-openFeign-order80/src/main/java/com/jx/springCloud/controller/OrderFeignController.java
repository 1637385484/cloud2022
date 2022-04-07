package com.jx.springCloud.controller;

import com.jx.springCloud.entities.CommonResult;
import com.jx.springCloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LDW
 * @date 2022/4/1 18:57
 */

@RestController
@Slf4j
public class OrderFeignController {

    private final PaymentFeignService paymentFeignService;

    public OrderFeignController(PaymentFeignService paymentFeignService) {
        this.paymentFeignService = paymentFeignService;
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id) {
        log.info("openfeign发起请求");
        return paymentFeignService.getPayment(id);
    }

    @GetMapping("/consumer/payment/timeOut")
    public String getTimeOut() {
        //openfeign默认等待一秒钟
        log.info("测试feign超时");
        return paymentFeignService.paymentFeignTimeOut();
    }
}