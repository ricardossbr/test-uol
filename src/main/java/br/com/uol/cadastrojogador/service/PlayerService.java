package br.com.uol.cadastrojogador.service;

import br.com.uol.cadastrojogador.dto.PlayerDto;
import br.com.uol.cadastrojogador.exceptions.NameIsNotAvailableException;
import br.com.uol.cadastrojogador.exceptions.PlayerIsNotFoundException;
import br.com.uol.cadastrojogador.model.PlayerModel;
import br.com.uol.cadastrojogador.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository repository;
    @Autowired
    private GroupService groupService;

    public ResponseEntity getPlayers(){
        final List<PlayerModel> players = this.repository.findAll();
        return ResponseEntity.ok(players.stream().collect(Collectors.groupingBy(p -> p.getGroupModel().getGroupName())));
    }

    public ResponseEntity<PlayerModel> getPlayer(Long id){
        final Optional<PlayerModel> player = this.repository.findById(id);
        if(player.isPresent()){
            return ResponseEntity.ok(player.get());
        }
        throw new PlayerIsNotFoundException("Player is not found");
    }

    public ResponseEntity save(PlayerDto playerDto) throws IOException {
        final PlayerModel playerModel = new PlayerModel();
        playerModel.setName(playerDto.getName());
        playerModel.setEmail(playerDto.getEmail());
        playerModel.setPhoneNumber(playerDto.getPhoneNumber());
        playerModel.setGroupModel(this.groupService.getGroup(playerDto.getGroupName().name()));
        try{
            final PlayerModel player = this.repository.save(playerModel);
            return ResponseEntity.ok(this.getUri(player));
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            throw new NameIsNotAvailableException("name is not available");
        }

    }

    public void deletePlayer(Long id){
        try {
            this.repository.deleteById(id);
        }catch (EmptyResultDataAccessException e ){
            throw new PlayerIsNotFoundException("Player is not found");
        }
    }

    public ResponseEntity<PlayerModel> alterPlayer(PlayerModel playerModel){
        try {
            return ResponseEntity.ok(this.repository.save(playerModel));
        }catch (EmptyResultDataAccessException e ){
            throw new PlayerIsNotFoundException("Player is not found");
        }
    }

    private URI getUri(PlayerModel playerModel) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(playerModel.getId()).toUri();
    }
}
