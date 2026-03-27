package com.guuh.user.infraestructure.exceptions;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String mensagem){super(mensagem);}

    public EmailNotFoundException(String mensagem, Throwable cause){super(mensagem,cause);}
}
