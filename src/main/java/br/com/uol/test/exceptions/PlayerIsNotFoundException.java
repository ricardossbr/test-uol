package br.com.uol.cadastrojogador.exceptions;

public class PlayerIsNotFoundException extends RuntimeException{

    public PlayerIsNotFoundException(){
        super("Player is not found");
    }

    public PlayerIsNotFoundException(String message){
        super(message);
    }

    public PlayerIsNotFoundException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }
}
