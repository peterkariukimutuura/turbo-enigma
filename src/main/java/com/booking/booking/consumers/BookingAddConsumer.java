package com.booking.booking.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BookingAddConsumer {
    private Logger logger = LoggerFactory.getLogger(BookingAddConsumer.class);
    @RabbitListener(queues="booking_add_queue")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        //latch.countDown();
    }
}
