package com.jx.springCloud.service;

import com.jx.springCloud.service.hystrixFallBack.PaymentFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author LDW
 * @date 2022/4/3 17:25
 */
@Component
//想要使得fallback生效则需要在yml配置文件中开启断路器
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallBackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/normal/{id}")
    String paymentInfo(@PathVariable("id") int id);

    @GetMapping("/payment/hystrix/timeOut/{id}")
    String paymentInfoTimeOut(@PathVariable("id") int id);

}
