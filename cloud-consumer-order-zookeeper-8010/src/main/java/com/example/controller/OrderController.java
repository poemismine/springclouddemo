package com.example.controller;

import com.example.entities.CommonResult;
import com.example.entities.Payment;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: tjy
 * @date: 2021/12/27 18:30
 */
@RestController
@Log4j2
public class OrderController {
//    private final String PAYMENT_SERVICE_URL = "http://127.0.0.1:8001";

    private final String PAYMENT_SERVICE_URL = "http://cloud-provider-payment-zookeeper";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/add")
    public CommonResult addPayment(Payment payment) {
        return restTemplate.postForObject(PAYMENT_SERVICE_URL + "/payment/add", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        ServiceInstance serviceInstance = discoveryClient.getInstances("cloud-provider-payment-zookeeper").get(0);
        return restTemplate.getForObject(PAYMENT_SERVICE_URL + "/payment/get/" + id, CommonResult.class);
    }

}
