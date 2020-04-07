package br.com.uol.cadastrojogador.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.uol.cadastrojogador.model.Jogador;

@Repository
public interface JogadorRepository extends CrudRepository<Jogador, Long>{

}
