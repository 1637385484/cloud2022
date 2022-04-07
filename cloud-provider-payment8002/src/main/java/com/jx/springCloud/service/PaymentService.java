package com.jx.springCloud.service;

import com.jx.springCloud.entities.Payment;

/**
 * @author LDW
 * @date 2022/3/23 18:38
 */
public interface PaymentService {
    /**
     * 创建一个支付订单
     */
    int create(Payment payment);

    /**
     * 通过payment的Id获取payment
     */
    Payment getPaymentById(long id);
}
