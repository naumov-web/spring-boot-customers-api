package ru.naumovweb.customersapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumovweb.customersapi.models.Customer;

public interface CustomerBaseRepository extends JpaRepository<Customer, Long> { }
