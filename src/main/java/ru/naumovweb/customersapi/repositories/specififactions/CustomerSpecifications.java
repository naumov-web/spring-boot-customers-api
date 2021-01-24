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

}
