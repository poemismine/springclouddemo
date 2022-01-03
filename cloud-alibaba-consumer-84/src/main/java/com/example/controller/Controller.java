package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @author: tjy
 * @date: 2021/12/31 19:46
 */
@RestController
public class Controller {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private RestTemplate restTemplate;

    private final String SERVICE_URL = "http://cloud-alibaba-provider";

    @GetMapping("/echo/consumer")
    public String echoAppName() {

        return restTemplate.getForObject(SERVICE_URL +"/echo/" + UUID.randomUUID(), String.class);
    }

}
