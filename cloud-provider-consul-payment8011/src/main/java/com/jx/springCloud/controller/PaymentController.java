package com.jx.springCloud.controller;

import ch.qos.logback.core.util.TimeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author LDW
 * @date 2022/3/30 16:39
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consul")
    public String paymentInfo() {
        return "springCloud with consul : " + serverPort + "  " + UUID.randomUUID();
    }
}
