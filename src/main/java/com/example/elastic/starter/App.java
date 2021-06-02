package com.example.elastic.starter;

import com.example.elastic.ValidationDTO;
import com.example.elastic.service.LoggerProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.function.Consumer;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
@Autowired
	ElasticsearchRestTemplate restTemplate;

@Bean
	public Consumer<ValidationDTO> loggerProcessor(){
		return new LoggerProcessor(restTemplate);
}
}
