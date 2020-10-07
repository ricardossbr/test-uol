package br.com.uol.cadastrojogador.exceptions;

public class HeroInconsistentWithTeamException extends Throwable {
    public HeroInconsistentWithTeamException(){
        super("Superhero is not available for this team");
    }

    public HeroInconsistentWithTeamException(String message){
        super(message);
    }

    public HeroInconsistentWithTeamException(String message, Throwable causeOfError){
        super(message, causeOfError);
    }
}
