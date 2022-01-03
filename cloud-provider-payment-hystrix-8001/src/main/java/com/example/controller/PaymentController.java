package com.example.controller;

import com.example.entities.CommonResult;
import com.example.entities.Payment;
import com.example.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: tjy
 * @date: 2021/12/27 18:30
 */
@RestController
@Log4j2
public class PaymentController {
    @Autowired
    @Qualifier("paymentServiceImpl")
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/add")
    public CommonResult addPayment(@RequestBody Payment payment) {
        Integer changes = paymentService.addPayment(payment);

        if (changes >= 1) {
            log.info("add payment success");
            return new CommonResult(200, serverPort + " add payment success");
        } else {
            log.info("add payment failed");
            return new CommonResult(500, serverPort + " add payment failed");
        }
    }

    @GetMapping(path = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);

        if (payment != null) {
            log.info("get payment success");
            return new CommonResult(200, serverPort + " get payment success", payment);
        } else {
            log.info("get payment failed");
            return new CommonResult(500, serverPort + " get payment failed");
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discover() {
        List<String> services = discoveryClient.getServices();
        log.info(discoveryClient.getInstances("cloud-provider-payment"));
        return services;
    }

    @GetMapping(value = "/payment/openfeign/timeout")
    public String timeout() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/payment/hystrix/timeout")
    @HystrixCommand(fallbackMethod = "hystrixTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String hystrixTimeout() {
        long seconds = 5;
//        int i = 10 / 0;
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort + " timeout";
    }

    public String hystrixTimeoutHandler() {
        return serverPort + " service failed";
    }

    @GetMapping(value = "/payment/hystrix/ok")
    public String hystrixOK() {
        return serverPort + " ok";
    }

    @GetMapping(value = "/payment/hystrix/circuit/{id}")
    public String hystrixCircleBreaker(@PathVariable("id") Integer id) {
        return serverPort + paymentService.hystrixCircleBreaker(id);
    }
}
