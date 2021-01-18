package ru.naumovweb.customersapi.dto.requests;

import lombok.Data;

@Data
public class RegisterDTO {
    private final String email;
    private final String password;
}
