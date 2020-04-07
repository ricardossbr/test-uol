package br.com.uol.cadastrojogador.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.uol.cadastrojogador.model.Grupo;

@Repository
public interface GrupoRepository extends CrudRepository<Grupo, String>{

}
