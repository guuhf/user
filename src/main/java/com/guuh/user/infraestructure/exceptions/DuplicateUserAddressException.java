package com.guuh.user.infraestructure.exceptions;

public class DuplicateUserAddressException extends RuntimeException{
    public DuplicateUserAddressException(String message) {
        super(message);
    }
    public DuplicateUserAddressException(String message, Throwable cause){super(message, cause);}
}
