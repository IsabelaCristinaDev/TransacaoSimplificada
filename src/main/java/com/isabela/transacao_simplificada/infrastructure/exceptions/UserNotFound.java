package com.isabela.transacao_simplificada.infrastructure.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        super(message);
    }
}
