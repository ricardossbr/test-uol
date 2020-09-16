package br.com.uol.cadastrojogador.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jdom2.JDOMException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import br.com.uol.cadastrojogador.service.PlayerService;
import br.com.uol.cadastrojogador.model.PlayerModel;
import br.com.uol.cadastrojogador.service.JogadorService;

@Controller
@AllArgsConstructor
public class PlayerController {

	@NonNull
	private PlayerService playerService;
	@NonNull
	private JogadorService jogadorService;
	
	@RequestMapping("/")
	public ResponseEntity listar() {
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/jogador" , method = RequestMethod.GET)
	public @ResponseBody  ResponseEntity lista() {
	   return new ResponseEntity(playerService.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/jogador", method = RequestMethod.POST)
	public ResponseEntity<List<PlayerModel>>  salvar(@Valid PlayerModel playerModel, BindingResult result) throws IOException {

		playerService.add(playerModel);
		return lista();
	}
	
	@RequestMapping(value = "/jogador/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable String id) {
		playerService.delete(id);
		if(paramsIsOk(id)){
			return ResponseEntity.ok(HttpStatus.OK);
		}
		return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/jogador/{id}", method = RequestMethod.PUT)
    public ResponseEntity edit(@PathVariable("id") String id) throws IOException {
		if(paramsIsOk(id)){
			return ResponseEntity.ok(HttpStatus.OK);
		}
		return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

	@RequestMapping(value = "/heroes" , method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getListHeroes() throws IOException, JDOMException{
		final Map<String, Object> zip = new HashMap<String, Object>();
		zip.put("liga", jogadorService.requestLiga());
		zip.put("vingadores", jogadorService.requestVingadores());
		return new ResponseEntity(zip, HttpStatus.OK);
	}

	private boolean paramsIsOk(String id){
		return !Objects.isNull(id) && !id.trim().isEmpty();
	}
	
}
