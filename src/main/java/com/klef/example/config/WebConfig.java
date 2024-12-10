package com.klef.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // CORS configuration
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow all paths
                .allowedOrigins("http://localhost:3000","https://jfsd-frontend-sage.vercel.app/") 
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow relevant HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true) // Enable credentials like cookies or authentication headers
                .maxAge(3600); // Cache the response for 3600 seconds
    }

    // Static resource handler
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:C:/uploads/") // Map the uploads directory
                .setCachePeriod(3600); // Optional: Set cache period for static files
    }
}
