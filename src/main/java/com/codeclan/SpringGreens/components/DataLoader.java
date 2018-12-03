package com.codeclan.SpringGreens.components;

import com.codeclan.SpringGreens.models.Booking;
import com.codeclan.SpringGreens.models.Customer;
import com.codeclan.SpringGreens.models.Table;
import com.codeclan.SpringGreens.repositories.BookingRepository.BookingRepository;
import com.codeclan.SpringGreens.repositories.CustomerRepository.CustomerRepository;
import com.codeclan.SpringGreens.repositories.TableRepository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TableRepository tableRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Table table1 = new Table(1);
        tableRepository.save(table1);

        Table table2 = new Table(2);
        tableRepository.save(table2);

        Table table3 = new Table(3);
        tableRepository.save(table3);

        Customer customer1 = new Customer("John", "john@codeclan.com", null);
        customerRepository.save(customer1);

        Customer customer2 = new Customer("Colin", "colin@codeclan.com", null);
        customerRepository.save(customer2);

        Customer customer3 = new Customer("Louise", "louise@codeclan.com", null);
        customerRepository.save(customer3);

        GregorianCalendar christmas = new GregorianCalendar(2018, Calendar.DECEMBER, 25, 16, 0, 0);
        Booking booking1 = new Booking(christmas, customer1, table1);
        bookingRepository.save(booking1);

        Booking booking2 = new Booking(christmas, customer2, table2);
        bookingRepository.save(booking2);

        GregorianCalendar hogmanay = new GregorianCalendar(2018, Calendar.DECEMBER, 31, 18, 0, 0);
        Booking booking3 = new Booking(hogmanay, customer1, table1);
        bookingRepository.save(booking3);

        Booking booking4 = new Booking(hogmanay, customer2, table2);
        bookingRepository.save(booking4);
    }
}
