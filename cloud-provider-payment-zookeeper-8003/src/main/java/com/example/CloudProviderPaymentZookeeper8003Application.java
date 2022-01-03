package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudProviderPaymentZookeeper8003Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudProviderPaymentZookeeper8003Application.class, args);
    }
}
