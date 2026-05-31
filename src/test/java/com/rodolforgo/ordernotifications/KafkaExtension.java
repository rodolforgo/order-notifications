package com.rodolforgo.ordernotifications;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.kafka.KafkaContainer;

class KafkaExtension implements BeforeAllCallback, AfterAllCallback {

    private static final KafkaContainer kafka = new KafkaContainer("apache/kafka:3.7.0");

    @Override
    public void beforeAll(ExtensionContext context) {
        kafka.start();
        System.setProperty("spring.kafka.bootstrap-servers", kafka.getBootstrapServers());
    }

    @Override
    public void afterAll(ExtensionContext context) {
        System.clearProperty("spring.kafka.bootstrap-servers");
    }
}
