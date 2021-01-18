package ru.naumovweb.customersapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumovweb.customersapi.models.User;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
