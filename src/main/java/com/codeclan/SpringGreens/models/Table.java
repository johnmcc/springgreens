package com.codeclan.SpringGreens.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name="tables")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int tableNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY)
    private List<Booking> bookings;

    public Table() {
    }

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.bookings = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
