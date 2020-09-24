package br.com.uol.cadastrojogador.cadastrojogador.builder;

import br.com.uol.cadastrojogador.dto.PlayerDto;
import br.com.uol.cadastrojogador.enums.GroupNameEnum;
import br.com.uol.cadastrojogador.model.PlayerModel;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.uol.cadastrojogador.cadastrojogador.builder.GroupTestBuilder.*;

public class PlayerTestBuilder {

    public static List<PlayerModel> getList() {
        return Arrays.asList(
                new PlayerModel( 1L, "Ricardo", "r@r.com", "11 12345-6789", getGroupModel()),
                new PlayerModel( 2L, "Ana", "a@a.com", "11 12345-6798", getGroupModel()),
                new PlayerModel( 3L, "Bruna", "b@b.com", "11 12345-6778", getGroupModel()),
                new PlayerModel( 4L, "Carla", "c@c.com", "11 12345-6787", getGroupModel()),
                new PlayerModel( 5L, "Daniela", "d@d.com", "11 12345-1234", getGroupModel()),
                new PlayerModel( 6L, "Eliana", "e@e.com", "11 12345-5678", getGroupModel()),
                new PlayerModel( 7L, "Fernanda", "f@f.com", "11 12345-1011", getGroupModel()),
                new PlayerModel( 8L, "Gisele", "g@g.com", "11 12345-1112", getGroupModel()),
                new PlayerModel( 9L, "Hellen", "h@h.com", "11 12345-1213", getGroupModel()),
                new PlayerModel( 10L, "Isabella", "i@i.com", "11 12345-1214", getGroupModel()),
                new PlayerModel( 11L, "Jessica", "j@j.com", "11 12345-1215", getGroupModel()),
                new PlayerModel( 12L, "Luana", "l@l.com", "11 12345-1216", getGroupModel())
        );
    }

    public static Optional<PlayerModel> getOptionalPlayerEmpty() {
        return Optional.empty();
    }

    public static Optional<PlayerModel> getOptionalPlayer() {
        return Optional.of(new PlayerModel( 1L, "Ricardo", "r@r.com", "11 12345-6789", getGroupModel()));
    }

    public static PlayerModel getPlayer() {
        return new PlayerModel( 1L, "Ricardo", "r@r.com", "11 12345-6789", getGroupModel());
    }

    public static PlayerModel getPlayerEmpty() {
        return new PlayerModel();
    }

    public static PlayerDto getPlayerDTO() {
        return new PlayerDto( "Ricardo Soares", "r@r.com", "11 12345-6789", GroupNameEnum.VINGADORES);
    }
}
