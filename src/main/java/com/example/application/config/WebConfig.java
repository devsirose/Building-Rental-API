package com.example.application.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {
    @Bean
    protected ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    @Bean
    protected RestOperations restTemplate() {
        return new RestTemplate();
    }
}
