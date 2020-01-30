package com.booking.booking.producers;

import com.booking.booking.models.Booking;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookingProducerService {
    private final RabbitTemplate rabbitTemplate;

    private final Exchange booking_exchange;

    public BookingProducerService(RabbitTemplate rabbitTemplate, Exchange booking_exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.booking_exchange = booking_exchange;
    }

    public void createBooking(Booking booking) {
        // ... do some database stuff
        String routingKey = "message.booking.add";
        rabbitTemplate.convertAndSend(booking_exchange.getName(), routingKey, booking);
    }
}
