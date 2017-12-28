package br.com.uol.cadastrojogador.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.uol.cadastrojogador.dao.GrupoRepository;

@Service
@Transactional
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;
	
	public boolean buscaPorCodNome(String codNome){
		return grupoRepository.findOne(codNome) == null;
	}

}
