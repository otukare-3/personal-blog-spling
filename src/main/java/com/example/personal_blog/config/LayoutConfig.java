package com.example.personal_blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
public class LayoutConfig {
    @Bean
    LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
