package com.codeclan.SpringGreens.repositories.BookingRepository;

import com.codeclan.SpringGreens.models.Booking;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class BookingRepositoryImpl implements BookingRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Booking> findBookingsByDate(GregorianCalendar date) {
        Session session = entityManager.unwrap(Session.class);
        List<Booking> results = null;

        try{
            Criteria cr = session.createCriteria(Booking.class);

            GregorianCalendar start = new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            GregorianCalendar finish = new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
            cr.add(Restrictions.between("datetime", start, finish));
            results = (ArrayList<Booking>) cr.list();
        }catch(HibernateException e){

        }finally{
            session.close();
        }

        return results;
    }

    @Override
    @Transactional
    public List<Booking> findBookingsByCustFrequency() {
        Session session = entityManager.unwrap(Session.class);
        List<Booking> results = null;

        try{
            Criteria cr = session.createCriteria(Booking.class);

            cr.setProjection(Projections.projectionList()
                            .add(Projections.count("id").as("numBookings"))
                            .add(Projections.groupProperty("customer")));

            cr.addOrder(Order.desc("numBookings"));
            results = cr.list();

        }catch(HibernateException ex){

        }finally{
            session.close();
        }

        return results;
    }
}
