package br.com.uol.cadastrojogador.repository;

import br.com.uol.cadastrojogador.model.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, Long> {

}
