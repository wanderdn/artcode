package com.example.elastic.config;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;

/**
 * This container wraps Confluent Schema Registry
 * To learn more about Schema Registry https://docs.confluent.io/current/schema-registry/schema_registry_tutorial.html
 *
 * @since 0.1
 */
public class SchemaRegistryContainer extends GenericContainer<SchemaRegistryContainer> {

    public SchemaRegistryContainer() {
        super("confluentinc/cp-schema-registry:5.5.1");
        withExposedPorts(8081);
    }

    public SchemaRegistryContainer withKafka(KafkaContainer kafka) {
        return withKafka(kafka.getNetwork(), kafka.getNetworkAliases().get(0) + ":9092");
    }

    public SchemaRegistryContainer withKafka(Network network, String bootstrapServers) {
        withNetwork(network);
        withEnv("SCHEMA_REGISTRY_HOST_NAME", "schema-registry");
        withEnv("SCHEMA_REGISTRY_LISTENERS", "http://0.0.0.0:8081");
        withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS", bootstrapServers);
        return self();
    }

    /**
     * Not very good name for this method ;)
     *
     * @deprecated use {@link #getSchemaRegistryUrl()}} instead.
     */
    @Deprecated
    public String getTarget() {
        return "http://" + getContainerIpAddress() + ":" + getMappedPort(8081);
    }

    /**
     * @return Schema Registry URL
     */
    public String getSchemaRegistryUrl() {
        return "http://" + getContainerIpAddress() + ":" + getMappedPort(8081);
    }
}
