package br.com.uol.cadastrojogador.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DetailErrorHttp {
    private String error;
    private String errorTitle;
    private final String errorMessageOfDeveloper = "Please verify the catalog of error, and good lucky my friend!";
    private final LocalDateTime errorDate = LocalDateTime.now();
}
