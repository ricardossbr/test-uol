package br.com.uol.cadastrojogador.exceptions;

public class SuperHeroIsNotAvailableException extends RuntimeException{

    public SuperHeroIsNotAvailableException(){
        super("Superhero is not available");
    }

    public SuperHeroIsNotAvailableException(String message){
        super(message);
    }

    public SuperHeroIsNotAvailableException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }
}
