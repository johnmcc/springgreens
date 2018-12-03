package com.codeclan.SpringGreens.repositories.BookingRepository;

import com.codeclan.SpringGreens.models.Booking;
import com.codeclan.SpringGreens.projections.EmbedForBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = EmbedForBooking.class)
public interface BookingRepository extends JpaRepository<Booking, Long>, BookingRepositoryCustom {
}
