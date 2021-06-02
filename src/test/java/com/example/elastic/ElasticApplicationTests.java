package com.example.elastic;

import com.example.elastic.config.SchemaRegistryContainer;
import com.example.elastic.config.TestAvroProducer;
import com.example.elastic.service.LoggerProcessor;
import com.example.elastic.starter.App;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.function.Consumer;


@Testcontainers
@Slf4j
@SpringBootTest
@ContextConfiguration(classes = App.class)
@DirtiesContext
@ActiveProfiles(value = "test")
//@EnableAutoConfiguration(exclude =org.springframework.boot.autoconfigure.kafka.KafkaProperties.class)
class ElasticApplicationTests {
    public static final Network testNe = Network.newNetwork();
    @Container
    public static final KafkaContainer container =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.4"))
                    .withEmbeddedZookeeper().withExposedPorts(9092, 9093, 2181)
                    .withNetwork(testNe).withNetworkAliases("kafka");
    @Container
    public static final SchemaRegistryContainer schemaRegistryContainer = schemaRegistryContainer(container);
    @Container
    private static final ElasticsearchContainer elasticsearchContainer =
            new WrappedElasticsearchContainer(
                    testNe);
@Autowired
    ApplicationContext context;

    public static SchemaRegistryContainer schemaRegistryContainer(KafkaContainer container) {
        return new SchemaRegistryContainer().withKafka(container).withNetworkAliases("schema");
    }


    @BeforeAll
    public static void init() {
        container.start();
        elasticsearchContainer.start();
        schemaRegistryContainer.start();
        System.setProperty(
                "elastic.host",
                elasticsearchContainer.getHttpHostAddress()
        );
        System.setProperty(
                "spring.application.cloud.stream.kafka.binder.brokers",
                container.getBootstrapServers()
        );
        System.setProperty(
                "spring.application.cloud.stream.binders.logger.environment.spring.cloud.stream.kafka.binder.brokers",
                container.getBootstrapServers()
        );
    }


    @Test
    void contextLoads() {
        LoggerProcessor processor  = (LoggerProcessor) context.getBean("loggerProcessor");
//        log.error(System.getPro/perty("spring.application.cloud.stream.binders.logger.environment.spring.cloud.stream.kafka.binder.brokers"));
        TestAvroProducer producer = new TestAvroProducer(schemaRegistryContainer.getSchemaRegistryUrl());
        producer.createProducer(container.getBootstrapServers());
        processor.accept(new ValidationDTO(false,""));

//        ValidationDTO validationDTO = new ValidationDTO(true, UUID.randomUUID().toString());
//        System.out.println(container.getLogs());
//        processor.accept(validationDTO);
    }
}

