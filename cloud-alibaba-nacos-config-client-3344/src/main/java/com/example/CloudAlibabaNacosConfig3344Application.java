package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CloudAlibabaNacosConfig3344Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaNacosConfig3344Application.class, args);
    }

}
