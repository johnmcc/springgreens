package com.codeclan.SpringGreens.repositories.TableRepository;

import com.codeclan.SpringGreens.models.Table;

import java.util.GregorianCalendar;

public interface TableRepositoryCustom {
    Table getFirstTableAtTime(GregorianCalendar cal);
}
