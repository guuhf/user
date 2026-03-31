package com.guuh.user.infraestructure.exceptions;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String mensagem){super(mensagem);}

    public AccessDeniedException(String mensagem, Throwable cause){super(mensagem, cause);}
}
