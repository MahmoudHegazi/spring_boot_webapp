package com.project.javausers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.project.javausers.model"})  // scan JPA entities
@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class
})
public class JavausersApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.project.javausers.JavausersApplication.class, args);
    }

}
