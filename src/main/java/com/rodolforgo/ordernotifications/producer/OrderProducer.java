package com.rodolforgo.ordernotifications.producer;

import com.rodolforgo.ordernotifications.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${kafka.topic.orders}")
    private String topic;

    public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Order order) {
        kafkaTemplate.send(topic, order.id(), order);
        System.out.printf("[PRODUCER] Pedido enviado: %s%n", order);
    }

    public void sendSync(Order order) {
        try {
            long inicio = System.currentTimeMillis();
            kafkaTemplate.send(topic, order.id(), order).get();
            Thread.sleep(1000); // simula latência de confirmação
            long duracao = System.currentTimeMillis() - inicio;
            System.out.printf("[PRODUCER] Pedido enviado (sync): %s — %dms%n", order, duracao);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao enviar pedido ao Kafka", e);
        }
    }


}
