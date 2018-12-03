package com.codeclan.SpringGreens.models;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@javax.persistence.Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private Table table;

    @Column
    private GregorianCalendar datetime;

    public Booking() {
    }

    public Booking(GregorianCalendar calendar, Customer customer, Table table) {
        this.datetime = calendar;
        this.customer = customer;
        this.table = table;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public GregorianCalendar getDatetime() {
        return datetime;
    }

    public void setDatetime(GregorianCalendar datetime) {
        this.datetime = datetime;
    }
}
