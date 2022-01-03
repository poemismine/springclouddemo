package com.example.service.Impl;

import com.example.entities.CommonResult;
import com.example.entities.Payment;
import com.example.service.PaymentOpenFeignService;
import org.springframework.stereotype.Component;

/**
 * @author: tjy
 * @date: 2021/12/30 14:09
 */
@Component
public class PaymentOpenFeignCallbackService implements PaymentOpenFeignService {
    @Override
    public CommonResult addPayment(Payment payment) {
        return new CommonResult(500, "addPayment service fail");
    }

    @Override
    public CommonResult getPaymentById(Long id) {
        return new CommonResult(500, "getPaymentById service fail");
    }

    @Override
    public String timeout() {
        return "timeout service fail";
    }

    @Override
    public String hystrixTimeout() {
        return "hystrixTimeout service fail";
    }

    @Override
    public String hystrixOK() {
        return "hystrixOK service fail";
    }
}
