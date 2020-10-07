package br.com.uol.test.repository;

import br.com.uol.test.enums.TeamEnum;
import br.com.uol.test.model.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Long> {
    List<GroupModel> findByTeam(TeamEnum team);
    boolean existsBySuperHero(String superHero);
}
