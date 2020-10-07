package br.com.uol.test.service;

import br.com.uol.test.dto.PlayerDto;
import br.com.uol.test.enums.TeamEnum;
import br.com.uol.test.exceptions.*;
import br.com.uol.test.model.GroupModel;
import br.com.uol.test.model.PlayerModel;
import br.com.uol.test.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    public ResponseEntity<Map<TeamEnum, List<PlayerModel>>> getPlayers(){
        final Map<TeamEnum, List<PlayerModel>> response = this.repository.findAll()
                .stream()
                .collect(Collectors.groupingBy(p -> p.getGroup().getTeam()));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<PlayerModel> getPlayer(Long id){
        final Optional<PlayerModel> player = this.repository.findById(id);
        if(player.isPresent()){
            return ResponseEntity.ok(player.get());
        }
        throw new PlayerIsNotFoundException();
    }

    public ResponseEntity save(PlayerDto playerDto) throws TeamIsFullException{
        final PlayerModel playerModel = new PlayerModel();
        if(playerDto != null){
            playerModel.setName(playerDto.getName());
            playerModel.setEmail(playerDto.getEmail());
            playerModel.setPhoneNumber(playerDto.getPhoneNumber());
            if(playerDto.getTeam() != null){
                playerModel.setGroup(this.groupService.getGroupBySave(playerDto.getTeam()));
                try{
                    final PlayerModel player = this.repository.save(playerModel);
                    return ResponseEntity.ok(this.getUri(player));
                }catch (DataIntegrityViolationException e){
                    e.printStackTrace();
                    throw new SuperHeroIsNotAvailableException();
                }
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<PlayerModel> alterPlayer(PlayerModel player) throws GroupIsRequiredExcepetion, HeroInconsistentWithTeamException {
        final Optional<PlayerModel> playerInDataBase = this.repository.findById(player.getId());
        if(playerInDataBase.isPresent()){
            final PlayerModel playerFound = playerInDataBase.get();
            playerFound.setName(player.getName());
            playerFound.setEmail(player.getEmail());
            playerFound.setPhoneNumber(player.getPhoneNumber());
            if(playerFound.getGroup().equals(player.getGroup())) {
                playerFound.setGroup(player.getGroup());
                return ResponseEntity.ok(this.repository.save(playerFound));
            }
            if(player.getGroup() != null &&
                    player.getGroup().getTeam() != null &&
                    player.getGroup().getSuperHero() != null)
            {
                final GroupModel group = this.groupService.getGroupByAlter(player.getGroup());
                if(group != null){
                    playerFound.setGroup(group);
                    return ResponseEntity.ok(this.repository.save(playerFound));
                }
                throw new HeroInconsistentWithTeamException();
            }
            throw new GroupIsRequiredExcepetion();
        }else{
            return ResponseEntity.notFound().build();    
        }
    }

    public void deletePlayer(Long id){
        try {
            this.repository.deleteById(id);
        }catch (EmptyResultDataAccessException e ){
            throw new PlayerIsNotFoundException();
        }
    }

    private URI getUri(PlayerModel playerModel) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(playerModel.getId()).toUri();
    }
}
