package com.guuh.user.infraestructure.exceptions;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException (String mensagem){super(mensagem);}

    public AddressNotFoundException (String mensagem, Throwable cause){super(mensagem,cause);}
}
