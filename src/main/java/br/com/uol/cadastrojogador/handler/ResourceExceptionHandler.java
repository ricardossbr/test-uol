package br.com.uol.cadastrojogador.handler;

import br.com.uol.cadastrojogador.dto.DetailErrorHttp;
import br.com.uol.cadastrojogador.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(HeroInconsistentWithTeamException.class)
    public ResponseEntity<DetailErrorHttp> handleHeroInconsistentWithTeamException(HeroInconsistentWithTeamException ex, HttpServletRequest request){
        final DetailErrorHttp error = new DetailErrorHttp();
        error.setError("400");
        error.setErrorTitle("This superhero is not available for this team, please verify the inconsistency and try again!  :)");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(SuperHeroIsNotAvailableException.class)
    public ResponseEntity<DetailErrorHttp> handleSuperHeroIsNotAvailableException(SuperHeroIsNotAvailableException ex, HttpServletRequest request){
        final DetailErrorHttp error = new DetailErrorHttp();
        error.setError("400");
        error.setErrorTitle("Sorry! This super hero is not available, please choose another hero :)");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TeamIsFullException.class)
    public ResponseEntity<DetailErrorHttp> handleTeamIsFullException(TeamIsFullException ex, HttpServletRequest request){
        final DetailErrorHttp error = new DetailErrorHttp();
        error.setError("400");
        error.setErrorTitle("Sorry! This team is full, please select another team :)");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(PlayerIsNotFoundException.class)
    public ResponseEntity<DetailErrorHttp> handlePlayerIsNotFoundException(PlayerIsNotFoundException ex, HttpServletRequest request){
        final DetailErrorHttp error = new DetailErrorHttp();
        error.setError("404");
        error.setErrorTitle("Sorry! This player is not found, please try save and go back here again :)");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ResourceHttpIsNotAvailableException.class)
    public ResponseEntity<DetailErrorHttp> handleResourceHttpIsNotAvailableException(ResourceHttpIsNotAvailableException ex, HttpServletRequest request){
        final DetailErrorHttp error = new DetailErrorHttp();
        error.setError("500");
        error.setErrorTitle("Sorry! We can't to help now, please try again in another moment :(");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(GroupIsRequiredExcepetion.class)
    public ResponseEntity<DetailErrorHttp> handleGroupIsRequiredExcepetion(GroupIsRequiredExcepetion ex, HttpServletRequest request){
        final DetailErrorHttp error = new DetailErrorHttp();
        error.setError("400");
        error.setErrorTitle("Sorry! Verify the group, some any error with team or hero... Please, correct and try again! :)  ");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}

