package br.com.uol.cadastrojogador.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.uol.cadastrojogador.model.PlayerModel;

@Repository
public interface PlayerRepository extends Repository<PlayerModel, Long>{

}
