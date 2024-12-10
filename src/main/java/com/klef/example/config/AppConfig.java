package com.klef.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.klef.example.utils.CSVParser;


@Configuration
public class AppConfig {

    @Bean
    public CSVParser csvParser() {
        return new CSVParser();
    }
}

