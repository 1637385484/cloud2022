package com.jx.springCloud.controller;

import com.jx.springCloud.entities.CommonResult;
import com.jx.springCloud.entities.Payment;
import com.jx.springCloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author LDW
 * @date 2022/3/23 18:46
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    //服务发现DiscoveryClient,用户获取服务信息,前提是要在主启动类中开启@EnableDiscoveryClient注解
    private final DiscoveryClient discoveryClient;

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService, DiscoveryClient discoveryClient) {
        this.paymentService = paymentService;
        this.discoveryClient = discoveryClient;
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int num = paymentService.create(payment);
        log.info("*****插入记录：" + num);
        log.info("*****插入记录：" + num);
        if (num > 0) {
            CommonResult success = new CommonResult(200, "数据插入成功,serverPort: " + serverPort);
            success.add("num", num);
            return success;
        }
        return new CommonResult(300, "数据插入失败");
    }

    @GetMapping("/get/{id}")
    public CommonResult getPayment(@PathVariable("id") long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("*****查询结果：" + paymentById);
        if (paymentById != null) {
            CommonResult success = new CommonResult(200, "查询成功,serverPort: " + serverPort);
            success.add("payment", paymentById);
            return success;
        } else {
            return new CommonResult(200, "查询失败");
        }
    }

    @GetMapping("/discovery")
    public Object discovery() {
        discoveryClient.getServices().forEach(log::info);//获取服务清单列表
        discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE").forEach(serviceInstance -> {
            log.info(serviceInstance.getServiceId());
            log.info(serviceInstance.getHost());
            log.info("" + serviceInstance.getPort());
            log.info(serviceInstance.getUri().toString());
        });//通过对外暴露的微服务名称，获取到具体的实例
        return discoveryClient;
    }

    @GetMapping("/timeOut")
    public String paymentFeignTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/getPort")
    public String getServerPort() {
        return serverPort;
    }
}
