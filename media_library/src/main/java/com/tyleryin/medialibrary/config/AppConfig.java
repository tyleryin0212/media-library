package com.tyleryin.medialibrary.config;

import com.tyleryin.medialibrary.in_memory_domain.Catalog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Catalog catalog() {
        return new Catalog();
    }
}
