package ru.naumovweb.customersapi.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.naumovweb.customersapi.models.Customer;
import ru.naumovweb.customersapi.repositories.CustomerBaseRepository;
import ru.naumovweb.customersapi.repositories.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private CustomerBaseRepository baseRepository;

    @Override
    public Customer save(Customer customer) {
        baseRepository.save(customer);
        return customer;
    }
}
