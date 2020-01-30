package com.booking.booking.controllers;

import com.booking.booking.ConfigureRabbitMq;
import com.booking.booking.models.Booking;
import com.booking.booking.producers.BookingProducerService;
import com.booking.booking.repositories.BookingRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/booking")
@RestController
public class BookingController {
    private final RabbitTemplate rabbitTemplate;

    public BookingController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    private BookingRepository bookingRepository;
    private BookingProducerService bookingProducerService;

    @GetMapping(path="/all",produces = "application/json")
    public List<Booking> getAllBookings(){

        return bookingRepository.findAll();

    }
    @PostMapping(path= "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addBooking(@RequestBody Booking booking){
//        rabbitTemplate.convertAndSend(ConfigureRabbitMq.MESSAGE_EXCHANGE_NAME, "message.booking.add",booking );
        bookingProducerService.createBooking(booking);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

//    @PutMapping(path= "/update", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<Object> updateBooking(@RequestParam@RequestBody Booking booking){
//        rabbitTemplate.convertAndSend(ConfigureRabbitMq.MESSAGE_EXCHANGE_NAME, "message.booking.add",booking );
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//
//    }

    @GetMapping(path="/findBooking/{id}",produces = "application/json")
    public Optional<Booking> findBooking(@PathVariable int id) {

       return bookingRepository.findById(id);

    }

}
