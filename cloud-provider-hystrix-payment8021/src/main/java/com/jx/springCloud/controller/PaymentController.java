package com.jx.springCloud.controller;

import com.jx.springCloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LDW
 * @date 2022/4/1 21:34
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment/hystrix/normal/{id}")
    public String paymentInfo(@PathVariable("id") int id) {
        String result = paymentService.paymentInfo(id);
        log.info(serverPort + " result :" + result);
        return result;
    }

    //系统异常或者超时都会走fallbackMethod(服务降级)
    @HystrixCommand(
            fallbackMethod = "paymentInfoTimeOutHandler",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            }
    )
    @GetMapping("/payment/hystrix/timeOut/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") int id) {
//        int i = 1 / 0;
        String result = paymentService.paymentInfoTimeOut(id);
        log.info(serverPort + " result :" + result);
        return result;
    }

    public String paymentInfoTimeOutHandler(int id) {
        return "线程池" + Thread.currentThread().getName() + "系统繁忙或异常，请稍后再试: " + id;
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") int id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("****result: " + result);
        return result;
    }

}
