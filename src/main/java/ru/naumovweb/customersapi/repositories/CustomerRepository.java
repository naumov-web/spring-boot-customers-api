package ru.naumovweb.customersapi.repositories;

import ru.naumovweb.customersapi.models.Customer;

public interface CustomerRepository {
    Customer save(Customer customer);
}
