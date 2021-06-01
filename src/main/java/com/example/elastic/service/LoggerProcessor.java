package com.example.elastic.service;

import com.example.elastic.ValidationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class LoggerProcessor implements Consumer<ValidationDTO> {
    private final ElasticsearchRestTemplate restTemplate;

    public LoggerProcessor(ElasticsearchRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void accept(ValidationDTO validationDTO) {
        log.warn("{}", validationDTO);
        restTemplate.save(validationDTO);
    }
}
