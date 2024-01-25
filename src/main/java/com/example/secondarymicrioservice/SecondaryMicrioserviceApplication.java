package com.example.secondarymicrioservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition
@EnableDiscoveryClient
@EnableJpaRepositories
public class SecondaryMicrioserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondaryMicrioserviceApplication.class, args);
    }

}
