package com.guuh.user.infraestructure.exceptions;

public class PhoneNotFoundException extends RuntimeException {
    public PhoneNotFoundException(String message) {
        super(message);
    }

    public PhoneNotFoundException(String message, Throwable cause){super(message, cause);}
}
