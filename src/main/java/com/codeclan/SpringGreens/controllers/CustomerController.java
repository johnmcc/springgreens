package com.codeclan.SpringGreens.controllers;

import com.codeclan.SpringGreens.models.Booking;
import com.codeclan.SpringGreens.repositories.BookingRepository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    BookingRepository bookingRepository;

    @GetMapping(value = "/by_frequency")
    public List<Booking> getCustomersByFrequency(){
        List<Booking> customers = bookingRepository.findBookingsByCustFrequency();
        return customers;
    }
}
