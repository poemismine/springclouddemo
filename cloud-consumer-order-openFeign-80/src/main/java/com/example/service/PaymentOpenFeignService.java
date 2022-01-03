package com.example.service;

import com.example.entities.CommonResult;
import com.example.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author: tjy
 * @date: 2021/12/29 22:10
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT")
public interface PaymentOpenFeignService {
    @PostMapping(value = "/payment/add")
    public CommonResult addPayment(@RequestBody Payment payment);

    @GetMapping(path = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/openfeign/timeout")
    public String timeout();
}
