package ru.naumovweb.customersapi.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UpdateCustomerDTO {
    @NotNull(message = "Name cannot be null")
    private final String name = null;

    @NotNull(message = "Description cannot be null")
    private final String description = null;
}
