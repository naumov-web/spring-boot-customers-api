package ru.naumovweb.customersapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.naumovweb.customersapi.dto.requests.LoginDTO;
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
public class AuthRestControllerV1 extends BaseRestController {

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
            return ResponseEntity.badRequest().body(mapBindingToResource(binding));
        }

        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());

        User registeredUser = userService.register(user);

        String token = jwtTokenProvider.createToken(registeredUser.getEmail(), registeredUser.getRoles());

        Map<Object, Object> response = new HashMap<>();
        response.put("email", registeredUser.getEmail());
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("login")
    public ResponseEntity login(@Valid @RequestBody final LoginDTO requestDto, final BindingResult binding) {

        if (binding.hasErrors()) {
            return ResponseEntity.badRequest().body(mapBindingToResource(binding));
        }
        try {
            String email = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
            User user = userService.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }

            String token = jwtTokenProvider.createToken(user.getEmail(), user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("email", user.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
