package com.codeclan.SpringGreens.repositories.BookingRepository;

import com.codeclan.SpringGreens.models.Booking;

import java.util.GregorianCalendar;
import java.util.List;

public interface BookingRepositoryCustom {
    List<Booking> findBookingsByDate(GregorianCalendar date);

    List<Booking> findBookingsByCustFrequency();
}
