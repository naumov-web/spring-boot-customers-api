package ru.naumovweb.customersapi.services;

import ru.naumovweb.customersapi.dto.common.ListItemsDTO;
import ru.naumovweb.customersapi.models.Customer;
import ru.naumovweb.customersapi.models.User;

import java.util.Optional;

/**
 * Service interface for class {@link Customer}.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

public interface CustomerService {

    Customer createForUser(User user, Customer customer);

    ListItemsDTO<Customer> indexForUser(User user, Integer size, Integer pageNumber, String sortBy, String sortDirection);

    Optional<Customer> findByIdForUser(User user, Long id);

    void delete(Long id);

    Customer update(Customer customer, String name, String description);

}
