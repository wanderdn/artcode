//package com.example.elastic;
//
//import com.example.elastic.config.KafkaConf;
//import com.example.elastic.config.SchemaRegistryContainer;
//import com.example.elastic.config.TestAvroProducer;
//import com.example.elastic.starter.App;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.testcontainers.containers.KafkaContainer;
//import org.testcontainers.containers.Network;
//import org.testcontainers.elasticsearch.ElasticsearchContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;
//
//import java.util.UUID;
//
//@SpringBootTest(classes = {App.class})
//@TestPropertySource(
//        locations = "classpath:application-test.properties")
//@Testcontainers
//@TestConfiguration
//@DirtiesContext
//@ExtendWith(SpringExtension.class)
//class AppTests {
//    public static final Network testNe = Network.newNetwork();
//    @Container
//    public static final KafkaContainer container =
//            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.4"))
//                    .withEmbeddedZookeeper().withExposedPorts(9092, 9093, 2181)
//                    .withNetwork(testNe).withNetworkAliases("kafka");
//    @Container
//    public static final SchemaRegistryContainer schemaRegistryContainer = schemaRegistryContainer(container);
//    @Container
//    private static final ElasticsearchContainer elasticsearchContainer = new ElasticsearchContainer();
//
//    @Test
//    void contextLoads() {
//        TestAvroProducer producer = new TestAvroProducer(schemaRegistryContainer.getSchemaRegistryUrl());
//        producer.createProducer("PLAINTEXT://0.0.0.0:" + 9092);
//        ValidationDTO validationDTO = new ValidationDTO(true, UUID.randomUUID().toString());
////        System.out.println(container.getLogs());
////        processor.accept(validationDTO);
//    }
//
//    public static SchemaRegistryContainer schemaRegistryContainer(KafkaContainer container) {
//        return new SchemaRegistryContainer().withKafka(container).withNetworkAliases("schema");
//    }
//
//    @BeforeAll
//    public static void init() {
//        container.start();
//        elasticsearchContainer.start();
//        System.setProperty(
//                "elastic.host",
//                elasticsearchContainer.getHttpHostAddress()
//        );
//        System.setProperty(
//                "spring.application.cloud.stream.kafka.binder.brokers",
//                container.getNetworkAliases().get(0) + ":9092"
//        );
//        System.setProperty(
//                "spring.application.cloud.stream.binders.logger.environment.spring.cloud.stream.kafka.binder.brokers",
//                container.getNetworkAliases().get(0) + ":9092"
//        );
//    }
//}
//
