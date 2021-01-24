package ru.naumovweb.customersapi.dto.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.naumovweb.customersapi.enums.StatusesEnum;
import ru.naumovweb.customersapi.models.Customer;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO {

    private Long id;
    private String name;
    private String description;
    private Date created_at;
    private Date updated_at;
    private StatusesEnum status;

    public static CustomerDTO fromCustomer(Customer origin) {
        return new CustomerDTO(
                origin.getId(),
                origin.getName(),
                origin.getDescription(),
                origin.getCreated_at(),
                origin.getUpdated_at(),
                origin.getStatus()
        );
    }

}
