package br.com.uol.cadastrojogador.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import br.com.uol.cadastrojogador.dao.JogadorDao;
import br.com.uol.cadastrojogador.model.Jogador;
import br.com.uol.cadastrojogador.model.ResponseHttp;
import br.com.uol.cadastrojogador.service.JogadorService;

@Controller
public class CadastroJogadorController {
	
	@Autowired
	private JogadorDao jogadorDao;
	@Autowired
	private JogadorService jogadorService;
	
	@RequestMapping("/")
	public ResponseEntity<ResponseHttp> listar() {
		return new ResponseEntity<ResponseHttp>(new ResponseHttp("It's Working!", 200), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lista" , method = RequestMethod.GET)
	public @ResponseBody  ResponseEntity<List<Jogador>> lista() {
	   return new ResponseEntity<List<Jogador>>(jogadorDao.getAll(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/list-heroes" , method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getListHeroes() throws IOException, JDOMException{
		final Map<String, Object> zip = new HashMap<String, Object>();
		zip.put("liga", jogadorService.requestLiga());
		zip.put("vingadores", jogadorService.requestVingadores());
		return new ResponseEntity<Map<String, Object>>(zip, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ResponseEntity<List<Jogador>>  salvar(@Valid Jogador jogador, BindingResult result) throws IOException {
		if(result.hasErrors()) {
            //return cadastro(jogador);
        }
		if(jogador.getGrupo().getCodNome().isEmpty()) {
			//jogador.setGrupo(new Grupo("?"+ ran.toString(), null));
		}
		jogadorDao.add(jogador);
		return lista();
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<List<Jogador>> delete(@PathVariable Long id) {
		jogadorDao.delete(id);
		return lista();
	}
	
	@GetMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("id") Long id) throws IOException {
        //return cadastro(jogadorDao.findById(id));
		return null;
    }
	
	
	
}
