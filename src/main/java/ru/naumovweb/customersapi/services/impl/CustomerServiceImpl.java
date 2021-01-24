package ru.naumovweb.customersapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumovweb.customersapi.dto.common.ListItemsDTO;
import ru.naumovweb.customersapi.enums.StatusesEnum;
import ru.naumovweb.customersapi.models.Customer;
import ru.naumovweb.customersapi.models.User;
import ru.naumovweb.customersapi.repositories.CustomerRepository;
import ru.naumovweb.customersapi.services.CustomerService;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createForUser(User user, Customer customer) {
        customer.setUser(user);
        customer.setStatus(StatusesEnum.ACTIVE);
        return customerRepository.save(customer);
    }

    @Override
    public ListItemsDTO<Customer> indexForUser(User user, Integer limit, Integer offset, String sortBy, String sortDirection) {
        return customerRepository.indexForUser(user, limit, offset, sortBy, sortDirection);
    }

    @Override
    public Optional<Customer> findByIdForUser(User user, Long id) {
        return customerRepository.findByIdForUser(user, id);
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(id);
    }

    @Override
    public Customer update(Customer customer, String name, String description) {
        customer.setName(name);
        customer.setDescription(description);

        return customerRepository.save(customer);
    }
}
