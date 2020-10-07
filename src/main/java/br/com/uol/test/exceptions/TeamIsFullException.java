package br.com.uol.test.exceptions;

public class TeamIsFullException extends Throwable {

    public TeamIsFullException(){
        super("This team is full");
    }

    public TeamIsFullException(String message){
        super(message);
    }

    public TeamIsFullException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }

}
