package com.application.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SpringBootConfig {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
