package ru.naumovweb.customersapi.services;

import ru.naumovweb.customersapi.models.User;

/**
 * Service interface for class {@link User}.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

public interface UserService {

    User register(User user);

    User findByEmail(String email);

}
