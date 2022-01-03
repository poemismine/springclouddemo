package com.example.service.Impl;

import com.example.dao.PaymentDao;
import com.example.entities.Payment;
import com.example.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: tjy
 * @date: 2021/12/27 17:33
 */
@Component
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public Integer addPayment(Payment payment) {
        return paymentDao.addPayment(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "hystrixCircuitBreakerHandler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
    })
    public String hystrixCircleBreaker(Integer id) {
        if (id <= 0) {
            throw new RuntimeException("id must be positive");
        }
        return "hystrixCircuitBreaker " + id;
    }

    @Override
    public String hystrixCircuitBreakerHandler(Integer id) {
        return "hystrixCircuitBreaker failed " + id;
    }
}
