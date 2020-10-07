package br.com.uol.test.exceptions;

public class ResourceHttpIsNotAvailableException extends Throwable {

    public ResourceHttpIsNotAvailableException(){
        super("Resource is not available!");
    }

    public ResourceHttpIsNotAvailableException(String message){
        super(message);
    }

    public ResourceHttpIsNotAvailableException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }

}
