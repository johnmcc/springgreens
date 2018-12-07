package com.codeclan.SpringGreens;

import com.codeclan.SpringGreens.models.Booking;
import com.codeclan.SpringGreens.models.Customer;
import com.codeclan.SpringGreens.models.Table;
import com.codeclan.SpringGreens.repositories.BookingRepository.BookingRepository;
import com.codeclan.SpringGreens.repositories.CustomerRepository.CustomerRepository;
import com.codeclan.SpringGreens.repositories.TableRepository.TableRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTableRepoCustom {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TableRepository tableRepository;

    @Autowired
    BookingRepository bookingRepository;

    private Table table1;
    private Table table2;
    private Table table3;

    @Before
    public void setUp() throws Exception {
        bookingRepository.deleteAll();
        tableRepository.deleteAll();
        customerRepository.deleteAll();

        table1 = new Table(1);
        tableRepository.save(table1);

        table2 = new Table(2);
        tableRepository.save(table2);

        table3 = new Table(3);
        tableRepository.save(table3);
    }

    @Test
    public void canGetTable__NoBookings() {
        List<Table> allTables = tableRepository.findAll();
        GregorianCalendar cal = new GregorianCalendar(2018, Calendar.DECEMBER, 25, 16, 0, 0);
        ArrayList<Long> ids = tableRepository.getExcludedTableIds(cal);
        Table foundTable = tableRepository.getFirstTableNotInArrayList(ids);

        assertEquals(1, foundTable.getTableNumber());
    }

    @Test
    public void canGetTable__BookingExists() {
        Customer customer = new Customer("John", "john@codeclan.com", null);
        customerRepository.save(customer);

        GregorianCalendar cal = new GregorianCalendar(2018, Calendar.DECEMBER, 25, 16, 0, 0);

        Booking booking = new Booking(cal, customer, table1);
        bookingRepository.save(booking);

        ArrayList<Long> ids = tableRepository.getExcludedTableIds(cal);
        Table foundTable = tableRepository.getFirstTableNotInArrayList(ids);
        assertEquals(2, foundTable.getTableNumber());
    }

    @Test
    public void cannotGetTable() {
        Customer customer = new Customer("John", "john@codeclan.com", null);
        customerRepository.save(customer);

        GregorianCalendar cal = new GregorianCalendar(2018, Calendar.DECEMBER, 25, 16, 0, 0);

        Booking booking1 = new Booking(cal, customer, table1);
        bookingRepository.save(booking1);

        Booking booking2 = new Booking(cal, customer, table2);
        bookingRepository.save(booking2);

        Booking booking3 = new Booking(cal, customer, table3);
        bookingRepository.save(booking3);

        ArrayList<Long> ids = tableRepository.getExcludedTableIds(cal);
        Table table = tableRepository.getFirstTableNotInArrayList(ids);

        assertNull(table);
    }
}
