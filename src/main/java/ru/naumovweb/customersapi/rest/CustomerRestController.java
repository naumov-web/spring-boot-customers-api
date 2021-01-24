package ru.naumovweb.customersapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.naumovweb.customersapi.dto.common.ListItemsDTO;
import ru.naumovweb.customersapi.dto.requests.CreateCustomerDTO;
import ru.naumovweb.customersapi.dto.resources.CustomerDTO;
import ru.naumovweb.customersapi.models.Customer;
import ru.naumovweb.customersapi.models.User;
import ru.naumovweb.customersapi.services.CustomerService;
import ru.naumovweb.customersapi.services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller for customer requests (create, index, update, delete, show)
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/account/customers")
public class CustomerRestController extends BaseRestController {

    private final CustomerService customerService;

    private final UserService userService;

    @Autowired
    public CustomerRestController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @PostMapping(value = "")
    public ResponseEntity create(@Valid @RequestBody final CreateCustomerDTO requestDto, final BindingResult binding) {
        if (binding.hasErrors()) {
            return ResponseEntity.badRequest().body(mapBindingToResource(binding));
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);

        Customer customer = new Customer();
        customer.setName(requestDto.getName());
        customer.setDescription(requestDto.getDescription());

        Customer newCustomer = customerService.createForUser(user, customer);

        Map<Object, Object> response = new HashMap<>();
        response.put("id", newCustomer.getId());
        response.put("name", newCustomer.getName());
        response.put("description", newCustomer.getDescription());
        response.put("status", newCustomer.getStatus());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity index(
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "sortDirection", required = false) String sortDirection
    ) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);

        ListItemsDTO<Customer> itemsDTO = customerService.indexForUser(
                user,
                size,
                pageNumber,
                sortBy,
                sortDirection
        );

        List<CustomerDTO> items = new ArrayList<>();

        for (int i = 0; i < itemsDTO.getItems().size(); i++) {
            items.add(
                    CustomerDTO.fromCustomer(itemsDTO.getItems().get(i))
            );
        }

        Map<Object, Object> response = new HashMap<>();
        response.put("pagesCount", itemsDTO.getPagesCount());
        response.put("items", items);

        return ResponseEntity.ok(response);
    }
}
