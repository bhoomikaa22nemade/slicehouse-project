package com.example.sliceeehouse.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dnt3dtvun",
                "api_key", "616765137165245",
                "api_secret", "I4J5oKPXehgt0lY52BhY4H-TQO4"
        ));
    }
}