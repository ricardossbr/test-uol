package br.com.uol.cadastrojogador.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

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
import org.springframework.web.servlet.ModelAndView;

import br.com.uol.cadastrojogador.helpers.JogadorHelper;
import br.com.uol.cadastrojogador.model.Grupo;
import br.com.uol.cadastrojogador.model.Jogador;
import br.com.uol.cadastrojogador.model.ResponseOk;
import br.com.uol.cadastrojogador.service.JogadorService;

@Controller
public class CadastroJogadorController {
	
	@Autowired
	private JogadorService jogadorService;
	@Autowired
	private JogadorHelper jogadorHelp;
	
	@RequestMapping("/")
	public ResponseEntity<ResponseOk> listar() {
		return new ResponseEntity<ResponseOk>(new ResponseOk("It's Working!", 200), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lista" , method = RequestMethod.GET)
	public @ResponseBody  ResponseEntity<List<Jogador>> lista() {
	   return new ResponseEntity<List<Jogador>>(jogadorService.getAll(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/cadastro" , method = RequestMethod.GET)
	public ModelAndView cadastro(Jogador jogador) throws IOException{
		List<String> liga = jogadorHelp.requestLiga();
		List<String> vingadores = jogadorHelp.requestVingadores();
		ModelAndView mv = new ModelAndView("/cadastro");
		mv.addObject("jogadores", jogador);
		mv.addObject("liga", liga);
		mv.addObject("vingadores", vingadores);
		return mv;
	}
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ResponseEntity<List<Jogador>>  salvar(@Valid Jogador jogador, BindingResult result) throws IOException {
		if(result.hasErrors()) {
            //return cadastro(jogador);
        }
		Random ran = new Random(); 
		if(jogador.getGrupo().getCodNome().isEmpty()) {
			//jogador.setGrupo(new Grupo("?"+ ran.toString(), null));
		}
		jogadorService.add(jogador);
		return lista();
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<List<Jogador>> delete(@PathVariable Long id) {
		jogadorService.delete(id);
		return lista();
	}
	
	@GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) throws IOException {
        return cadastro(jogadorService.findById(id));
    }
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
