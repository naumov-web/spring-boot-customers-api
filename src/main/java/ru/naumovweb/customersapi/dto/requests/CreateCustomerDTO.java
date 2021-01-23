package ru.naumovweb.customersapi.dto.requests;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateCustomerDTO {

    @NotNull(message = "Name cannot be null")
    private final String name = null;

    @NotNull(message = "Description cannot be null")
    private final String description = null;
}
