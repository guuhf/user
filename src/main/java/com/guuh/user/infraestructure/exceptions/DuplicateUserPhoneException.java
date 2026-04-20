package com.guuh.user.infraestructure.exceptions;

public class DuplicateUserPhoneException extends RuntimeException{
    public DuplicateUserPhoneException(String message) {
        super(message);
    }
    public DuplicateUserPhoneException(String message, Throwable cause){super(message, cause);}
}
