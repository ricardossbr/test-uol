package br.com.uol.cadastrojogador.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.uol.cadastrojogador.model.GrupModel;

@Repository
public interface GrupRepository extends CrudRepository<GrupModel, String>{

}
