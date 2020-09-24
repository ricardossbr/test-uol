package br.com.uol.cadastrojogador.service;

import br.com.uol.cadastrojogador.enums.GroupNameEnum;
import br.com.uol.cadastrojogador.exceptions.NameIsNotAvailableException;
import br.com.uol.cadastrojogador.exceptions.ResourceHttpIsNotAvailableException;
import br.com.uol.cadastrojogador.model.GroupModel;
import br.com.uol.cadastrojogador.dto.SuperHeroJson;
import br.com.uol.cadastrojogador.dto.SuperHeroXml;
import br.com.uol.cadastrojogador.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupService {

   @Autowired
   private GroupRepository repository;

   @Autowired
   private ResourceHttpService httpService;

   public GroupModel getGroup(String groupName) throws IOException, ResourceHttpIsNotAvailableException {
      final List<GroupModel> groupModels = this.repository.findByGroupName(groupName);
      final GroupModel groupModel = new GroupModel();
      groupModel.setGroupName(groupName);
      if(groupName.equals(GroupNameEnum.VINGADORES.name())){
         final SuperHeroJson heroJson = this.httpService.requestVingadores();
         final List<String> codNames = heroJson.getVingadores().stream()
                 .map(r -> r.get("codinome")).collect(Collectors.toList());
         groupModel.setName(this.getAvailableNames(groupModels, codNames));
      }else{
         final SuperHeroXml heroXml = this.httpService.requestLiga();
         groupModel.setName(this.getAvailableNames(groupModels, heroXml.getCodNames()));
      }
      return groupModel;
   }

   private String getAvailableNames(List<GroupModel> groupModels, List<String> codNames) {
      final List<String> availableNames = new ArrayList<>(codNames);
      if(groupModels.size() > 1){
         groupModels.stream().forEach(to -> availableNames.removeIf(r -> r.equals(to.getName())));
      }
      if(availableNames.size() > 1){
         return availableNames.get(new Random().nextInt(availableNames.size()));
      }
      throw new NameIsNotAvailableException();
   }
}
