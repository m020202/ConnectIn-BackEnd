package com.Connect_In.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/payments/confirm")
                .allowedOrigins("http://43.202.7.120")
                .allowedMethods("POST")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
