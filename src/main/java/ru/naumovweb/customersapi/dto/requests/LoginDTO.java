package ru.naumovweb.customersapi.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LoginDTO {

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private final String email = null;

    @NotNull(message = "Password cannot be null")
    private final String password = null;
}
