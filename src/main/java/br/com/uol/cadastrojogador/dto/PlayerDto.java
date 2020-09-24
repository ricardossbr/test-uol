package br.com.uol.cadastrojogador.dto;

import br.com.uol.cadastrojogador.enums.GroupNameEnum;
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
    private GroupNameEnum groupName;
}
