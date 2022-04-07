package com.jx.springCloud.service.hystrixFallBack;

import com.jx.springCloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @author LDW
 * @date 2022/4/4 15:36
 */
@Component
@SuppressWarnings("all")
public class PaymentFallBackService implements PaymentHystrixService {

    @Override
    public String paymentInfo(int id) {
        return "paymentFallbackService fall back-paymentInfo";
    }

    @Override
    public String paymentInfoTimeOut(int id) {
        return "paymentFallbackService fall back-paymentInfoTimeOut";
    }

}
