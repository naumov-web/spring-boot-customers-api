package ru.naumovweb.customersapi.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * Authentication exception for JwtAppDemo application.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
