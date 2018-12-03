package com.codeclan.SpringGreens.repositories.CustomerRepository;

import com.codeclan.SpringGreens.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {
}
