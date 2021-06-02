package com.example.elastic.service;

import com.example.elastic.ValidationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Qualifier("loggerProcessor")
@Slf4j
public class LoggerProcessor implements Consumer<ValidationDTO> {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public LoggerProcessor(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }


    @Override
    public void accept(ValidationDTO validationDTO) {
        log.warn("{}", validationDTO);
        elasticsearchRestTemplate.save(validationDTO);
    }

    @Override
    public String toString() {
        return "LoggerProcessor{" +
                "elasticsearchRestTemplate=" + elasticsearchRestTemplate +
                '}';
    }
}
