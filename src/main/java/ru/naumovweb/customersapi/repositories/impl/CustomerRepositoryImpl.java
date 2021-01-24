package ru.naumovweb.customersapi.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import ru.naumovweb.customersapi.dto.common.ListItemsDTO;
import ru.naumovweb.customersapi.enums.PageDefinitionsEnum;
import ru.naumovweb.customersapi.models.Customer;
import ru.naumovweb.customersapi.models.User;
import ru.naumovweb.customersapi.repositories.CustomerBaseRepository;
import ru.naumovweb.customersapi.repositories.CustomerRepository;
import ru.naumovweb.customersapi.repositories.specififactions.CustomerSpecifications;

import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private CustomerBaseRepository baseRepository;

    @Override
    public Customer save(Customer customer) {
        baseRepository.save(customer);
        return customer;
    }

    @Override
    public ListItemsDTO<Customer> indexForUser(User user, Integer size, Integer pageNumber, String sortBy, String sortDirection) {
        ListItemsDTO<Customer> dto = new ListItemsDTO<>();
        Pageable pageable = null;
        Sort sort = null;

        if (size == null) { size = PageDefinitionsEnum.SIZE; }
        if (pageNumber == null) { pageNumber = PageDefinitionsEnum.PAGE_NUMBER; }
        if (sortBy != null && sortDirection != null) {
            sort = Sort.by(sortBy);
            if (sortDirection.equals("desc")) {
                sort = sort.descending();
            }
        }

        if (sort != null) {
            pageable = PageRequest.of(pageNumber, size, sort);
        } else {
            pageable = PageRequest.of(pageNumber, size);
        }

        Page<Customer> pageDto = baseRepository.findAll(CustomerSpecifications.filterByUser(user), pageable);

        dto.setItems(pageDto.getContent());
        dto.setPagesCount(pageDto.getTotalPages());

        return dto;
    }

    @Override
    public Optional<Customer> findByIdForUser(User user, Long id) {
        return baseRepository.findOne(CustomerSpecifications.filterByUserAndId(user, id));
    }

    @Override
    public void delete(Long id) {
        baseRepository.deleteById(id);
    }
}
