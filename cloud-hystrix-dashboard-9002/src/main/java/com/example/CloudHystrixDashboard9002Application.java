package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class CloudHystrixDashboard9002Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudHystrixDashboard9002Application.class, args);
    }

}
