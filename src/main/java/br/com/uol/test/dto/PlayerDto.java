package br.com.uol.test.dto;

import br.com.uol.test.enums.TeamEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerDto {
    private String name;
    private String email;
    private String phoneNumber;
    private TeamEnum team;
}
