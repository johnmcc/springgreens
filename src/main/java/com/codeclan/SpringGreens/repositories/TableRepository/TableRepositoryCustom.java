package com.codeclan.SpringGreens.repositories.TableRepository;

import com.codeclan.SpringGreens.models.Table;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface TableRepositoryCustom {
    Table getFirstTableNotInArrayList(ArrayList<Long> ids);
    ArrayList<Long> getExcludedTableIds(GregorianCalendar cal);
}
