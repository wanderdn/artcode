package com.example.elastic.config;

import com.example.elastic.ValidationDTO;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
public class TestAvroProducer {

    private final String schemaRegistryUrl;
    ValidationDTO movie = new ValidationDTO(false, UUID.randomUUID().toString());

    public TestAvroProducer(final String schemaRegistryUrl) {
        this.schemaRegistryUrl = schemaRegistryUrl;
    }

    public Map<String, Object> createProducerProperties(final String bootstrapServer) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put("input.topic.name", "external-integration-message-log");
        props.put("input.topic.partitions", "1");
        props.put("input.topic.replication.factor", "1");

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // TODO: make named param and null check ¯\_(ツ)_/¯
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, LoggingProducerInterceptor.class.getName());

        return props;
    }

    public void createProducer(final String bootstrapServer) {

        Map<String, Object> props = createProducerProperties(bootstrapServer);
        createTopic(bootstrapServer);
        System.out.println(bootstrapServer);
        KafkaProducer<String, Object> producer = new KafkaProducer<>(props);

        long numberOfEvents = 5;
        for (int i = 0; i < numberOfEvents; i++) {
            String key = UUID.randomUUID().toString();
            ProducerRecord<String, Object> record = new ProducerRecord<>(
                    (String) props.get("input.topic.name"), key, movie);
            try {
                producer.send(record).get();
//                log.error("{}",record.value());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        producer.close();
    }

    private void createTopic(String map) {
        Map<String, Object> conf = new HashMap<>();
        conf.put("bootstrap.servers", map);
        AdminClient client = AdminClient.create(conf);
        List<NewTopic> topics = new ArrayList<>();
        topics.add(new NewTopic("external-integration-message-log", 1, (short) 1
        ));
        client.createTopics(topics);
    }
}


