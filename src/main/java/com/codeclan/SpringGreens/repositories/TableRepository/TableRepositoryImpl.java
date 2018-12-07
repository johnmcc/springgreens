package com.codeclan.SpringGreens.repositories.TableRepository;

import com.codeclan.SpringGreens.models.Booking;
import com.codeclan.SpringGreens.models.Table;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class TableRepositoryImpl implements TableRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Autowired
    TableRepository tableRepository;

    @Transactional
    @Override
    public ArrayList<Long> getExcludedTableIds(GregorianCalendar cal) {
        Session session = entityManager.unwrap(Session.class);
        List<Booking> bookingsPerDate = null;
        ArrayList<Long> ids = null;

        try {
            Criteria cr = session.createCriteria(Booking.class);
            cr.add(Restrictions.eq("datetime", cal));

            bookingsPerDate = cr.list();

            ids = new ArrayList<>();

            for(Booking booking: bookingsPerDate){
                ids.add(booking.getTable().getId());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
//            session.close();
        }

        return ids;
    }

    @Transactional
    @Override
    public Table getFirstTableNotInArrayList(ArrayList<Long> ids){
        Session session = entityManager.unwrap(Session.class);
        List<Table> results = null;

        try {
            Criteria cr = session.createCriteria(Table.class);
            if(ids != null && ids.size() > 0){
                cr.add(Restrictions.not(Restrictions.in("id", ids)));
            }

            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
//            session.close();
        }

        if(results.size() > 0){
            return results.get(0);
        }

        return null;
    }
}
