package com.example.elastic.config;

import com.example.elastic.ValidationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class KafkaConf {

    @Bean
    public Consumer<ValidationDTO> processor(
            @Autowired Consumer<ValidationDTO> loggerProcessor

    ) {
        return loggerProcessor;
    }
}
