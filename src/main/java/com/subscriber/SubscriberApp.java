package com.subscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.subscriber.repositories")
@ComponentScan(basePackages = { "com.subscriber.*" })
@EntityScan("com.subscriber.entities")
@EnableAutoConfiguration(exclude = WebMvcAutoConfiguration.class)
public class SubscriberApp {

    public static void main(String[] args) {
        SpringApplication.run(SubscriberApp.class, args);
    }
}
