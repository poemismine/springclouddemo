package com.example.controller;

import com.example.entities.CommonResult;
import com.example.entities.Payment;
import com.example.service.PaymentOpenFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tjy
 * @date: 2021/12/27 18:30
 */
@RestController
@Log4j2
@DefaultProperties(defaultFallback = "consumerHystrixGlobalHandler")
public class OrderController {
//    private final String PAYMENT_SERVICE_URL = "http://127.0.0.1:8001";

//    private final String PAYMENT_SERVICE_URL = "http://CLOUD-PROVIDER-PAYMENT";

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private PaymentOpenFeignService paymentOpenFeignService;

    @Value("${server.port}")
    private String serverPort;

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

    @GetMapping(value = "/consumer/payment/hystrix/timeout")
    public String hystrixTimeout() {
        return paymentOpenFeignService.hystrixTimeout();
    }

    @GetMapping(value = "/consumer/payment/hystrix/ok")
    public String hystrixOK() {
        return paymentOpenFeignService.hystrixOK();
    }

    @GetMapping(value = "/consumer/payment/hystrix/test/timeout")
//    @HystrixCommand(fallbackMethod = "consumerHystrixTimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
//    })
    @HystrixCommand
    public String consumerHystrixTimeout() {
        return paymentOpenFeignService.hystrixTimeout();
    }

    public String consumerHystrixTimeoutHandler() {
        return serverPort + " consumer service failed";
    }

    public String consumerHystrixGlobalHandler() {
        return serverPort + " global consumer service failed";
    }
}
