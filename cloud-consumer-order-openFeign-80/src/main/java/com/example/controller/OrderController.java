package com.example.controller;

import com.example.entities.CommonResult;
import com.example.entities.Payment;
import com.example.service.PaymentOpenFeignService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tjy
 * @date: 2021/12/27 18:30
 */
@RestController
@Log4j2
public class OrderController {
//    private final String PAYMENT_SERVICE_URL = "http://127.0.0.1:8001";

//    private final String PAYMENT_SERVICE_URL = "http://CLOUD-PROVIDER-PAYMENT";

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private PaymentOpenFeignService paymentOpenFeignService;

    @GetMapping(value = "/consumer/payment/add")
    public CommonResult addPayment(Payment payment) {
        return paymentOpenFeignService.addPayment(payment);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        return paymentOpenFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/openfeign/timeout")
    public String timeout() {
        return paymentOpenFeignService.timeout();
    }
}
