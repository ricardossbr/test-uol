package br.com.uol.cadastrojogador.exceptions;

public class NameIsNotAvailableException extends RuntimeException{

    public NameIsNotAvailableException(String message){
        super(message);
    }

    public NameIsNotAvailableException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }
}
