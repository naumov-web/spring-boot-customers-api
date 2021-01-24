package ru.naumovweb.customersapi.services;

import ru.naumovweb.customersapi.dto.common.ListItemsDTO;
import ru.naumovweb.customersapi.models.Customer;
import ru.naumovweb.customersapi.models.User;

/**
 * Service interface for class {@link Customer}.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

public interface CustomerService {

    public Customer createForUser(User user, Customer customer);

    public ListItemsDTO<Customer> indexForUser(User user, Integer size, Integer pageNumber, String sortBy, String sortDirection);

}
