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

@Service
public class GroupService {

   @Autowired
   private GroupRepository repository;

   @Autowired
   private ResourceHttpService httpService;

   public GroupModel getGroupBySave(TeamEnum team) throws TeamIsFullException {
      final List<GroupModel> groupModels = this.repository.findByTeam(team);
      final GroupModel groupModel = new GroupModel();
      groupModel.setTeam(team);
      if(team.equals(TeamEnum.THE_AVENGERS)){
         final SuperHeroJson heroJson = this.httpService.requestVingadores();
         final List<String> codNames = heroJson.getVingadores().stream()
                 .map(r -> r.get("codinome")).collect(Collectors.toList());
         groupModel.setSuperHero(this.getHeroAvailableBySave(groupModels, codNames));
      }else{
         final SuperHeroXml heroXml = this.httpService.requestLiga();
         groupModel.setSuperHero(this.getHeroAvailableBySave(groupModels, heroXml.getCodNames()));
      }
      return groupModel;
   }

   private String getHeroAvailableBySave(List<GroupModel> groupModels, List<String> codNames) throws TeamIsFullException {
      final List<String> availableNames = new ArrayList<>(codNames);
      if(groupModels.size() > 1){
         groupModels.stream().forEach(to -> availableNames.removeIf(r -> r.equals(to.getSuperHero())));
      }
      if(availableNames.size() > 1){
         return availableNames.get(new Random().nextInt(availableNames.size()));
      }
      throw new TeamIsFullException();
   }

   public GroupModel getGroupByAlter(GroupModel group) {
      final boolean isAvailable = !this.repository.existsBySuperHero(group.getSuperHero());
      if(isAvailable){
         final GroupModel groupModel = new GroupModel();
         groupModel.setTeam(group.getTeam());
         if(group.getTeam().equals(TeamEnum.THE_AVENGERS)){
            final SuperHeroJson heroJson = this.httpService.requestVingadores();
            final List<String> codNames = heroJson.getVingadores().stream()
                    .map(r -> r.get("codinome"))
                    .collect(Collectors.toList());
            groupModel.setSuperHero(this.getHeroAlter(codNames, group.getSuperHero()));
         }else{
            final SuperHeroXml heroXml = this.httpService.requestLiga();
            groupModel.setSuperHero(this.getHeroAlter(heroXml.getCodNames(), group.getSuperHero()));

         }
         return groupModel.getSuperHero() != null ? groupModel : null;
      }
      throw new SuperHeroIsNotAvailableException();
   }

   private String getHeroAlter(List<String> codNames, String heroVerify) {
      final List<String> heros = codNames.stream()
              .filter(r -> r.equals(heroVerify)).collect(Collectors.toList());
      if (heros.size() == 1) {
         return heros.get(0);
      }
      return null;
   }
}
