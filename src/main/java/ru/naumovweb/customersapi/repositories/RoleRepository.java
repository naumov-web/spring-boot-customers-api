package ru.naumovweb.customersapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumovweb.customersapi.models.Role;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link Role}.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
