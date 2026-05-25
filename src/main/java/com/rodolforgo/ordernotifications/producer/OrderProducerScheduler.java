package com.rodolforgo.ordernotifications.producer;

import com.rodolforgo.ordernotifications.model.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class OrderProducerScheduler {

    private final OrderProducer producer;
    private final List<String> items = List.of("Milho", "Pamonha", "Canjica", "Arrumadinho");
    private final Random random = new Random();

    public OrderProducerScheduler(OrderProducer producer) {
        this.producer = producer;
    }

    @Scheduled(fixedDelay = 3000)
    public void scheduleOrder() {
        var order = new Order(
            UUID.randomUUID().toString().substring(0, 8),
            items.get(random.nextInt(items.size())),
            "CREATED"
        );
        producer.send(order);
    }
}
