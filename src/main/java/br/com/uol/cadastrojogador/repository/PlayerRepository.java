package br.com.uol.cadastrojogador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.uol.cadastrojogador.model.PlayerModel;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, Long> {

}
