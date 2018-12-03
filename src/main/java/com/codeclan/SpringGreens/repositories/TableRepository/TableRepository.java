package com.codeclan.SpringGreens.repositories.TableRepository;

import com.codeclan.SpringGreens.models.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Long>, TableRepositoryCustom {
}
