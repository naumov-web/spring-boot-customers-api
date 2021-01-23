package ru.naumovweb.customersapi.services;

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

}
