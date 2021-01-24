package ru.naumovweb.customersapi.repositories;

import ru.naumovweb.customersapi.dto.common.ListItemsDTO;
import ru.naumovweb.customersapi.models.Customer;
import ru.naumovweb.customersapi.models.User;

import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);

    ListItemsDTO<Customer> indexForUser(User user, Integer size, Integer pageNumber, String sortBy, String sortDirection);

    Optional<Customer> findByIdForUser(User user, Long id);

    void delete(Long id);
}
