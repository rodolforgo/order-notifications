package com.rodolforgo.ordernotifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderNotificationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderNotificationsApplication.class, args);
    }

}
