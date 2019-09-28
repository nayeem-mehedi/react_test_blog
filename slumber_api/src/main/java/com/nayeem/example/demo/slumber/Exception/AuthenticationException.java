package com.nayeem.example.demo.slumber.Exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
