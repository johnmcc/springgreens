package com.codeclan.SpringGreens.projections;

import com.codeclan.SpringGreens.models.Customer;
import com.codeclan.SpringGreens.models.Table;

import java.util.GregorianCalendar;

public interface EmbedForBooking {
    Long getId();
    Customer getCustomer();
    Table getTable();
    GregorianCalendar getDatetime();
}
