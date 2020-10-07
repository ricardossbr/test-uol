package br.com.uol.test.controller;

import br.com.uol.test.dto.PlayerDto;
import br.com.uol.test.enums.TeamEnum;
import br.com.uol.test.exceptions.GroupIsRequiredExcepetion;
import br.com.uol.test.exceptions.HeroInconsistentWithTeamException;
import br.com.uol.test.exceptions.TeamIsFullException;
import br.com.uol.test.model.PlayerModel;
import br.com.uol.test.service.PlayerService;
import br.com.uol.test.service.ResourceHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController()
public class PlayerController {

	@Autowired
	private ResourceHttpService resourceHttpService;

	@Autowired
	private PlayerService playerService;

	@GetMapping(value = "/teams", produces="application/json", consumes="application/json")
	public ResponseEntity<List<String>> availableTeams() {
		return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(TeamEnum.JUSTICE_LEAGUE.name(), TeamEnum.THE_AVENGERS.name()));
	}

	@GetMapping(value = "/player", produces="application/json", consumes="application/json")
	public ResponseEntity listAllPlayers() {
		return this.playerService.getPlayers();
	}

	@GetMapping(value = "/player/{id}", produces="application/json", consumes="application/json")
	public ResponseEntity list(@PathVariable("id") Long id) {
		return this.playerService.getPlayer(id);
	}
	
	@PostMapping(value = "/player", produces="application/json", consumes="application/json")
	public ResponseEntity  save(@RequestBody PlayerDto PlayerDto) throws TeamIsFullException {
		return playerService.save(PlayerDto);
	}
	
	@DeleteMapping(value = "/player/{id}", produces="application/json", consumes="application/json")
	public ResponseEntity delete(@PathVariable Long id) {
		this.playerService.deletePlayer(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/player/{id}", produces="application/json", consumes="application/json")
    public ResponseEntity edit(@RequestBody PlayerModel playerModel, @PathVariable Long id) throws GroupIsRequiredExcepetion, HeroInconsistentWithTeamException {
		if(playerModel != null && id != null){
			playerModel.setId(id);
			return this.playerService.alterPlayer(playerModel);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
