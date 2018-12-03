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
import java.util.GregorianCalendar;
import java.util.List;

public class TableRepositoryImpl implements TableRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public Table getFirstTableAtTime(GregorianCalendar cal) {
        Session session = entityManager.unwrap(Session.class);
        Table table = null;

        try {
            Criteria cr = session.createCriteria(Table.class);

            List<Table> tables = cr.list();

            for(Table singleTable: tables){
                Session newSession = entityManager.unwrap(Session.class);

                Criteria bookingCr = newSession.createCriteria(Booking.class);

                bookingCr.add(Restrictions.eq("table", singleTable));
                bookingCr.add(Restrictions.eq("datetime", cal));

                List<Booking> bookingResults = bookingCr.list();
                if(bookingResults.size() == 0){
                    table = singleTable;
                    break;
                }

                newSession.close();
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return table;

    }
}
