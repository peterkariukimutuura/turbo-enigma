package com.booking.booking;

import com.booking.booking.consumers.BookingAddConsumer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureRabbitMq {

    public static final String MESSAGE_EXCHANGE_NAME = "message_exchange";
    public static final String BOOKING_EXCHANGE_NAME = "booking_exchange";
    public static final String AUDIT_QUEUE_NAME = "message_audit_queue";
    public static final String ADD_QUEUE_NAME = "booking_add_queue";
    public static final String EDIT_QUEUE_NAME = "booking_edit_queue";
    public static final String DELETE_QUEUE_NAME = "booking_delete_queue";

    @Bean
    Queue MessageAuditQueue(){
        return new Queue(AUDIT_QUEUE_NAME,false);
    }

    @Bean
    Queue BookingAddQueue(){
        return new Queue(ADD_QUEUE_NAME,false);
    }

    @Bean
    Queue BookingEditQueue(){
        return new Queue(EDIT_QUEUE_NAME,false);
    }

    @Bean
    Queue BookingDeleteQueue(){
        return new Queue(DELETE_QUEUE_NAME,false);
    }

    @Bean
    TopicExchange message_exchange(){
        return new TopicExchange(MESSAGE_EXCHANGE_NAME);
    }

    @Bean
    TopicExchange booking_exchange(){
        return new TopicExchange(BOOKING_EXCHANGE_NAME);
    }

    @Bean
    Binding exchange_binding(TopicExchange message_exchange, TopicExchange booking_exchange) {
        return BindingBuilder.bind(message_exchange).to(booking_exchange).with("message.booking.#");
    }

    @Bean
    Binding audit_binding(Queue MessageAuditQueue, TopicExchange message_exchange) {
        return BindingBuilder.bind(MessageAuditQueue).to(message_exchange).with("message.#");
    }

    @Bean
    Binding edit_booking_binding(Queue BookingEditQueue, TopicExchange booking_exchange) {
        return BindingBuilder.bind(BookingEditQueue).to(booking_exchange).with("booking.#");
    }

    @Bean
    Binding add_booking_binding(Queue BookingAddQueue, TopicExchange booking_exchange) {
        return BindingBuilder.bind(BookingAddQueue).to(booking_exchange).with("booking.#");
    }

    @Bean
    Binding delete_booking_binding(Queue BookingDeleteQueue, TopicExchange booking_exchange) {
        return BindingBuilder.bind(BookingDeleteQueue).to(booking_exchange).with("booking.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter messageListenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(ADD_QUEUE_NAME,AUDIT_QUEUE_NAME,EDIT_QUEUE_NAME,DELETE_QUEUE_NAME);
        container.setMessageListener(messageListenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter addBookingAdapter(BookingAddConsumer handler) {
        return new MessageListenerAdapter(handler, "receiveMessage");
    }
}
