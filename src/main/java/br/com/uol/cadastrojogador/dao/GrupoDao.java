package br.com.uol.cadastrojogador.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GrupoDao {

	@Autowired
	private GrupoRepository grupoRepository;
	
	public boolean buscaPorCodNome(String codNome){
		return grupoRepository.findOne(codNome) == null;
	}

}
