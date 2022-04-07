package com.jx.springCloud.service.serviceImpl;

import com.jx.springCloud.dao.PaymentDao;
import com.jx.springCloud.entities.Payment;
import com.jx.springCloud.service.PaymentService;
import org.springframework.stereotype.Service;

/**
 * @author LDW
 * @date 2022/3/23 18:39
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDao paymentDao;

    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public int create(Payment payment) {
        return paymentDao.insert(payment);
    }

    public Payment getPaymentById(long id) {
        return paymentDao.selectById(id);
    }
}
