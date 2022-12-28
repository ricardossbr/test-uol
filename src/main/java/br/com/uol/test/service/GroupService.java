package br.com.uol.test.service;

import br.com.uol.test.dto.SuperHeroJson;
import br.com.uol.test.dto.SuperHeroXml;
import br.com.uol.test.enums.TeamEnum;
import br.com.uol.test.exceptions.SuperHeroIsNotAvailableException;
import br.com.uol.test.exceptions.TeamIsFullException;
import br.com.uol.test.model.GroupModel;
import br.com.uol.test.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GroupService {

   @Autowired
   private GroupRepository repository;

   @Autowired
   private ResourceHttpService httpService;

   public List<String> getAvailableAvengers(){
      return this.getHeroAvengers()
              .filter(r -> !this.repository.existsBySuperHero(r))
              .collect(Collectors.toList());
   }

   public List<String> getLeague(){
      return this.getHeroJusticeLeague()
              .filter(r -> !this.repository.existsBySuperHero(r))
              .collect(Collectors.toList());
   }


   public GroupModel getGroupBySave(TeamEnum team) throws TeamIsFullException {
      final List<GroupModel> groupModels = this.repository.findByTeam(team);
      final GroupModel groupModel = new GroupModel();
      groupModel.setTeam(team);
      groupModel.setSuperHero(
              this.getHeroAvailableBySave(groupModels,
                      team.equals(TeamEnum.THE_AVENGERS)
                              ? getHeroAvengers().collect(Collectors.toList())
                              :getHeroJusticeLeague().collect(Collectors.toList())
              )
      );
      return groupModel;
   }

   private String getHeroAvailableBySave(List<GroupModel> groupModels, List<String> codNames) throws TeamIsFullException {
      final List<String> availableNames = new ArrayList<>(codNames);
      if(groupModels.size() > 1){
         groupModels.stream().forEach(to -> availableNames.removeIf(r -> r.equals(to.getSuperHero())));
      }
      if(!availableNames.isEmpty()){
         return availableNames.get(new Random().nextInt(availableNames.size()));
      }
      throw new TeamIsFullException();
   }

   public GroupModel getGroupByAlter(GroupModel group) {
      final boolean isAvailable = !this.repository.existsBySuperHero(group.getSuperHero());
      if(isAvailable){
         final GroupModel groupModel = new GroupModel();
         groupModel.setTeam(group.getTeam());
         groupModel.setSuperHero(
                 group.getTeam().equals(TeamEnum.THE_AVENGERS)
                 ? this.getHeroJsonAlter(group.getSuperHero())
                 : this.getHeroXmlAlter(group.getSuperHero())
         );
         return groupModel.getSuperHero() != null ? groupModel : null;
      }
      throw new SuperHeroIsNotAvailableException();
   }

   public void deleteByGroup(GroupModel group){
      this.repository.delete(group);
   }

   private String getHeroXmlAlter(String heroVerify) {
      final List<String> heroes = getHeroJusticeLeague()
              .filter(r -> r.equals(heroVerify))
              .collect(Collectors.toList());
      if (!heroes.isEmpty()) {
         return heroes.get(0);
      }
      return null;
   }

   private String getHeroJsonAlter(String heroVerify) {
      final List<String> heroes = getHeroAvengers()
              .filter(r -> r.equals(heroVerify))
              .collect(Collectors.toList());
      if (!heroes.isEmpty()) {
         return heroes.get(0);
      }
      return null;
   }

   private Stream<String> getHeroJusticeLeague() {
      return this.httpService.requestJusticeLeague()
              .getCodNames()
              .stream()
              .parallel();
   }

   private Stream<String> getHeroAvengers() {
      return this.httpService.requestTheAvengers().getVingadores()
              .stream()
              .parallel()
              .map(r -> r.get("codinome"));
   }
}
