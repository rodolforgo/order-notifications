package com.rodolforgo.ordernotifications.controller;

import com.rodolforgo.ordernotifications.model.Order;
import com.rodolforgo.ordernotifications.producer.OrderProducer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderProducer producer;

    public OrderController(OrderProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/sync")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Order> createSync(@RequestBody List<Order> orders) {
        orders.forEach(producer::sendSync);
        return orders;
    }

    @PostMapping("/async")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Order> createAsync(@RequestBody List<Order> orders) {
        orders.forEach(producer::send);
        return orders;
    }
}
