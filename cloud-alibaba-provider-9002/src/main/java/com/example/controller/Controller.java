package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tjy
 * @date: 2021/12/31 19:46
 */
@RestController
public class Controller {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/echo/{id}")
    public String echo(@PathVariable String id) {

        return "Hello Nacos " + serverPort + " Discovery " + id;
    }
}
