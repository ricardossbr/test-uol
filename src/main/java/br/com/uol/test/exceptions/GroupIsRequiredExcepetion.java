package br.com.uol.test.exceptions;

public class GroupIsRequiredExcepetion extends Throwable {

    public GroupIsRequiredExcepetion(){
        super("Group is required");
    }

    public GroupIsRequiredExcepetion(String message){
        super(message);
    }

    public GroupIsRequiredExcepetion(String message, Throwable causeOfError){
        super(message, causeOfError);
    }

}
