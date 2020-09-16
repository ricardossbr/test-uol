package br.com.uol.cadastrojogador.service;

import javax.transaction.Transactional;

import br.com.uol.cadastrojogador.repository.GrupRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GrupService {

	@NonNull
	private GrupRepository grupRepository;
	
	public boolean buscaPorCodNome(String codNome){
		return grupRepository.findOne(codNome) == null;
	}
}
