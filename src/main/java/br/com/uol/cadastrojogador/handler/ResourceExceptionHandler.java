package br.com.uol.cadastrojogador.handler;

import br.com.uol.cadastrojogador.dto.DetailErrorHttp;
import br.com.uol.cadastrojogador.exceptions.NameIsNotAvailableException;
import br.com.uol.cadastrojogador.exceptions.PlayerIsNotFoundException;
import br.com.uol.cadastrojogador.exceptions.ResourceHttpIsNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NameIsNotAvailableException.class)
    public ResponseEntity<DetailErrorHttp> handleNameIsNotAvailableException(NameIsNotAvailableException ex, HttpServletRequest request){
        final DetailErrorHttp error = new DetailErrorHttp();
        error.setError("400");
        error.setErrorTitle("Sorry! This group is full, please try other group :)");
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
}

