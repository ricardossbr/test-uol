package br.com.uol.cadastrojogador.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.uol.cadastrojogador.dao.JogadorRepository;
import br.com.uol.cadastrojogador.model.Jogador;

@Service
@Transactional
public class JogadorService {
	
	
	@Autowired
	private JogadorRepository jogadorRepository;
	
	public List<Jogador> getAll(){
		return (List<Jogador>) jogadorRepository.findAll();
	}
	
	public Jogador add(Jogador jogador) {
		jogadorRepository.save(jogador);
		return jogador;
	}
	
	public Jogador findById(Long id) {
		return jogadorRepository.findOne(id);
	}
	
	
	public void delete(Long id) {
		jogadorRepository.delete(id);
	}
	

}
