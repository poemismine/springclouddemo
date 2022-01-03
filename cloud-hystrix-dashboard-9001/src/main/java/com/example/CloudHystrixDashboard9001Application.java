package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author: tjy
 * @date: 2021/12/30 17:21
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableHystrixDashboard
public class CloudHystrixDashboard9001Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudHystrixDashboard9001Application.class);
    }
}
