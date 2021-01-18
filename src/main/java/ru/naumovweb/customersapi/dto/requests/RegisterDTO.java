package ru.naumovweb.customersapi.dto.requests;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RegisterDTO {

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private final String email = null;

    @NotNull(message = "Password cannot be null")
    private final String password = null;
}
