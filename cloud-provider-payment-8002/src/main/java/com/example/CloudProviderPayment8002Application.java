package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: tjy
 * @date: 2021/12/27 17:33
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.example"})
@EnableEurekaClient
@EnableDiscoveryClient
public class CloudProviderPayment8002Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudProviderPayment8002Application.class, args);
    }
}
