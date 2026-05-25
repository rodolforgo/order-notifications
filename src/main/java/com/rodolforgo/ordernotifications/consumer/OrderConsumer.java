package com.rodolforgo.ordernotifications.consumer;

import com.rodolforgo.ordernotifications.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @KafkaListener(topics = "${kafka.topic.orders}", groupId = "notification-service")
    public void consume(Order order) {
        System.out.printf("[NOTIFICAÇÃO] Novo pedido #%s: %s — %s%n",
            order.id(), order.item(), order.status());
    }
}
