package ru.naumovweb.customersapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.naumovweb.customersapi.dto.requests.RegisterDTO;
import ru.naumovweb.customersapi.models.User;
import ru.naumovweb.customersapi.security.jwt.JwtTokenProvider;
import ru.naumovweb.customersapi.services.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for authentication requests (register, login)
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody final RegisterDTO requestDto, final BindingResult binding) {

        if (binding.hasErrors()) {
            Map<Object, Object> response = new HashMap<>();

            binding.getFieldErrors().forEach(fieldError -> {
                response.put(fieldError.getField(), fieldError.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(response);
        }

        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());

        User registeredUser = userService.register(user);

        String token = jwtTokenProvider.createToken(registeredUser.getEmail(), registeredUser.getRoles());

        Map<Object, Object> response = new HashMap<>();
        response.put("email", requestDto.getEmail());
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
