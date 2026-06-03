package com.wipro.DIDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public Address address() {
        return new Address("jalandhar");
    }

    @Bean
    public Student student() {
        return new Student(11, "Sakshi", address());
    }
}