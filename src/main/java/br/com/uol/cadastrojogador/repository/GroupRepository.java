package br.com.uol.cadastrojogador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.uol.cadastrojogador.model.GroupModel;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Long> {
    List<GroupModel> findByGroupName(String groupName);

}
