package br.com.uol.cadastrojogador.controller;

import br.com.uol.cadastrojogador.dto.PlayerDto;
import br.com.uol.cadastrojogador.enums.GroupNameEnum;
import br.com.uol.cadastrojogador.service.PlayerService;
import br.com.uol.cadastrojogador.service.ResourceHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class PlayerController {

	@Autowired
	private ResourceHttpService resourceHttpService;

	@Autowired
	private PlayerService playerService;

	@GetMapping(value = "/groups")
	public ResponseEntity<List<String>> availableGroups() throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList(GroupNameEnum.LIGADAJUSTICA.name(), GroupNameEnum.VINGADORES.name()));
	}
	
	@GetMapping(value = "/player/{id}")
	public @ResponseBody  ResponseEntity list(@PathVariable("id") Long id) {
	   return this.playerService.getPlayer(id);
	}
	
	@PostMapping(value = "/player")
	public ResponseEntity  save(@RequestBody PlayerDto playerModel) throws IOException {
		if(playerModel != null){
			return playerService.save(playerModel);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(value = "/player/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@PutMapping(value = "/player/{id}")
    public ResponseEntity edit(@PathVariable("id") Long id) throws IOException {
		return ResponseEntity.ok(HttpStatus.OK);

    }
}
