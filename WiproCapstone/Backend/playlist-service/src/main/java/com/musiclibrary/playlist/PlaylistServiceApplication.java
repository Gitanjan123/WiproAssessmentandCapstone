package com.musiclibrary.playlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure
        .SpringBootApplication;
import org.springframework.cloud.client.discovery
        .EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer
        .LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class PlaylistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(
            PlaylistServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}