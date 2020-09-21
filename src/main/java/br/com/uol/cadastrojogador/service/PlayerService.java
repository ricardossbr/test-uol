package br.com.uol.cadastrojogador.service;

import br.com.uol.cadastrojogador.dto.PlayerDto;
import br.com.uol.cadastrojogador.exceptions.PlayerIsNotFoundException;
import br.com.uol.cadastrojogador.model.PlayerModel;
import br.com.uol.cadastrojogador.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository repository;
    @Autowired
    private GroupService groupService;

    public ResponseEntity save(PlayerDto playerDto) throws IOException {
        final PlayerModel playerModel = new PlayerModel();
        playerModel.setName(playerDto.getName());
        playerModel.setEmail(playerDto.getEmail());
        playerModel.setGroupModel(this.groupService.getGroup(playerDto.getGroupName().name()));
        return ResponseEntity.ok(this.getUri(this.repository.save(playerModel)));
    }

    public ResponseEntity<PlayerModel> getPlayer(Long id){
        final Optional<PlayerModel> player = this.repository.findById(id);
        if(player.isPresent()){
            return ResponseEntity.ok(player.get());
        }
        throw new PlayerIsNotFoundException("Player is not found");
    }

    private URI getUri(PlayerModel playerModel) {
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(playerModel.getId()).toUri();
        return uri;
    }
}
