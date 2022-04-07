package com.jx.springCloud.controller;

import com.jx.springCloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LDW
 * @date 2022/4/3 17:36
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")//该注解配合HystrixCommand可作全局统一降级
public class OrderHystrixController {

    private final PaymentHystrixService paymentHystrixService;

    public OrderHystrixController(PaymentHystrixService paymentHystrixService) {

        this.paymentHystrixService = paymentHystrixService;
    }

    @GetMapping("/consumer/payment/hystrix/normal/{id}")
    public String paymentInfo(@PathVariable("id") int id) {
        String info = paymentHystrixService.paymentInfo(id);
        log.info(info);
        return info;
    }

    /*
        服务降级
        在消费端的超时有两种情况：
            1.HystrixCommand注解的超时时间大于openfeign的超时时间
                这时会由于超时触发paymentInfoTimeOutFallBackMethod方法
            2.HystrixCommand注解的超时时间小于openfeign的超时时间
                这时会由于openfeign的超时时间导致报异常，则也会被HystrixCommand捕捉
                从而触发paymentInfoTimeOutFallBackMethod方法
     */
//    @HystrixCommand(
//            fallbackMethod = "paymentInfoTimeOutFallBackMethod",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//            }
//    )
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeOut/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") int id) {
//        int i = 1 / 0; 如果是这里发生的异常，则交给defaultFallback来进行处理
        //如果是由于服务器故障发生的问题则优先交给paymentHystrixService的实现类来进行处理
        String info = paymentHystrixService.paymentInfoTimeOut(id);
        log.info(info);
        return info;
    }

    @SuppressWarnings("all")
    public String paymentInfoTimeOutFallBackMethod(@PathVariable("id") int id) {
        return "支付繁忙，请稍后再试 " + id;
    }

    @SuppressWarnings("all")
    public String paymentGlobalFallbackMethod() {
        return "全局异常处理";
    }

}
