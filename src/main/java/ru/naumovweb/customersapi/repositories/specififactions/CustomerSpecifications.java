package ru.naumovweb.customersapi.repositories.specififactions;

import org.springframework.data.jpa.domain.Specification;
import ru.naumovweb.customersapi.models.Customer;
import ru.naumovweb.customersapi.models.User;

public class CustomerSpecifications {

    public static Specification<Customer> filterByUser(User user) {
        return (root, query, cb) -> {
            return cb.equal(root.<String>get("user"), user);
        };
    }

    public static Specification<Customer> filterByUserAndId(User user, Long id) {
        return (root, query, cb) -> {
            return cb.and(
                    cb.equal(root.<String>get("user"), user),
                    cb.equal(root.<String>get("id"), id)
            );
        };
    }

}
