package com.jx.springCloud.service;

import com.jx.springCloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author LDW
 * @date 2022/4/1 18:43
 */

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/get/{id}")
    CommonResult getPayment(@PathVariable("id") long id);

    @GetMapping("/payment/timeOut")
    String paymentFeignTimeOut();
}
