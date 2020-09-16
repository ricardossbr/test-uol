package br.com.uol.cadastrojogador.service;

import java.util.List;

import br.com.uol.cadastrojogador.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import br.com.uol.cadastrojogador.model.PlayerModel;

@Service
@AllArgsConstructor
public class PlayerService {
	
	@NonNull
	private PlayerRepository playerRepository;
	
	public List<PlayerModel> getAll(){

		return playerRepository.findAll();
	}
	
	public PlayerModel add(PlayerModel playerModel) {
		return playerRepository.save(playerModel);
	}
	
	public PlayerModel findById(String id) {
		return playerRepository.findOne(Long.parseLong(id));
	}
	
	
	public void delete(String id) {
		playerRepository.delete(Long.parseLong(id));
	}
	
}
