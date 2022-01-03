package com.example.service;

import com.example.entities.Payment;

/**
 * @author: tjy
 * @date: 2021/12/27 18:25
 */
public interface PaymentService {
    Integer addPayment(Payment payment);

    Payment getPaymentById(Long id);

    public String hystrixCircleBreaker(Integer id);

    public String hystrixCircuitBreakerHandler(Integer id);
}
