package com.ProyectoIntegrador.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String mensaje){
        super(mensaje);
    }
}
