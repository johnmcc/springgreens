package com.codeclan.SpringGreens.controllers;

import com.codeclan.SpringGreens.models.Booking;
import com.codeclan.SpringGreens.models.Customer;
import com.codeclan.SpringGreens.models.Table;
import com.codeclan.SpringGreens.repositories.BookingRepository.BookingRepository;
import com.codeclan.SpringGreens.repositories.CustomerRepository.CustomerRepository;
import com.codeclan.SpringGreens.repositories.TableRepository.TableRepository;
import helpers.ObjectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TableRepository tableRepository;

    @CrossOrigin
    @GetMapping(value = "/date/{year}/{month}/{day}")
    public List<Booking> getBookingsByDate(@PathVariable int year, @PathVariable int month, @PathVariable int day){
        GregorianCalendar gDate = new GregorianCalendar(year, month - 1, day, 0, 0, 0);
        List<Booking> bookings = bookingRepository.findBookingsByDate(gDate);

        return bookings;
    }

    @CrossOrigin
    @RequestMapping(value = "/new", headers = "Accept=application/json", method = RequestMethod.POST)
    public Booking createNewBooking(@RequestBody HashMap<String, String> requestData){

        String customerStr = requestData.get("customer");
        Long id = ObjectBuilder.findIdFromString(customerStr);
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        // Parse datetime > Gregorian
        Instant instant = Instant.parse(requestData.get("datetime") + ":00.0Z");
        Date date = Date.from(instant);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);

        // Find first table
        Table table = tableRepository.getFirstTableAtTime(cal);

        if(table != null && optionalCustomer != null){
            Customer customer = optionalCustomer.get();
            // Save new booking
            Booking booking = new Booking(cal, customer, table);
            bookingRepository.save(booking);
            return booking;
        }

        return null;
    }
}
