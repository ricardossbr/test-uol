package br.com.uol.cadastrojogador.cadastrojogador.builder;

import br.com.uol.cadastrojogador.dto.SuperHeroJson;
import br.com.uol.cadastrojogador.dto.SuperHeroXml;
import br.com.uol.cadastrojogador.enums.TeamEnum;
import br.com.uol.cadastrojogador.model.GroupModel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GroupTestBuilder {

    public static GroupModel getGroupModelXml(){
        final List<GroupModel> groupModels = Arrays.asList(
                new GroupModel(TeamEnum.JUSTICE_LEAGUE, "Lanterna Verde"),
                new GroupModel(TeamEnum.JUSTICE_LEAGUE, "Flash"),
                new GroupModel(TeamEnum.JUSTICE_LEAGUE,"Aquaman"),
                new GroupModel(TeamEnum.JUSTICE_LEAGUE, "Batman"),
                new GroupModel(TeamEnum.JUSTICE_LEAGUE,"Superman"),
                new GroupModel(TeamEnum.JUSTICE_LEAGUE, "Mulher Maravilha"));
        final int element = new Random().nextInt(groupModels.size());
        return groupModels.get(element);
    }

    public static GroupModel getGroupModelJson(){
        final List<GroupModel> groupModels = Arrays.asList(
                new GroupModel(TeamEnum.THE_AVENGERS, "Capitão América"),
                new GroupModel(TeamEnum.THE_AVENGERS, "Hulk"),
                new GroupModel(TeamEnum.THE_AVENGERS, "Pantera Negra"),
                new GroupModel(TeamEnum.THE_AVENGERS, "Homem de Ferro"),
                new GroupModel(TeamEnum.THE_AVENGERS, "Thor"),
                new GroupModel(TeamEnum.THE_AVENGERS, "Feiticeira Escarlate"),
                new GroupModel(TeamEnum.THE_AVENGERS, "Visão"));
        final int element = new Random().nextInt(groupModels.size());
        return groupModels.get(element);
    }

    public static List<GroupModel> getList(){
        return Arrays.asList(
                new GroupModel(TeamEnum.THE_AVENGERS, "Capitão América"),
                new GroupModel(TeamEnum.THE_AVENGERS, "Hulk"),
                new GroupModel(TeamEnum.THE_AVENGERS, "Pantera Negra"),
                new GroupModel(TeamEnum.THE_AVENGERS, "Homem de Ferro"),
                new GroupModel(TeamEnum.THE_AVENGERS, "Thor"),
                new GroupModel(TeamEnum.THE_AVENGERS, "Feiticeira Escarlate"),
                new GroupModel(TeamEnum.THE_AVENGERS, "Visão"),
                new GroupModel(TeamEnum.JUSTICE_LEAGUE, "Lanterna Verde"),
                new GroupModel(TeamEnum.JUSTICE_LEAGUE, "Flash"),
                new GroupModel(TeamEnum.JUSTICE_LEAGUE,"Aquaman"),
                new GroupModel(TeamEnum.JUSTICE_LEAGUE, "Batman"),
                new GroupModel(TeamEnum.JUSTICE_LEAGUE,"Superman"),
                new GroupModel(TeamEnum.JUSTICE_LEAGUE, "Mulher Maravilha"));
    }

    public static List<GroupModel> getListEmpty(){
        return Arrays.asList();
    }

    public static SuperHeroJson getSuperHeroJson(){
        final SuperHeroJson json = new SuperHeroJson();
        json.setVingadores(Arrays.asList(
                Map.of("codinome", "Capitão América"),
                Map.of("codinome", "Pantera Negra"),
                Map.of("codinome", "Homem de Ferro"),
                Map.of("codinome", "Thor"),
                Map.of("codinome", "Feiticeira Escarlate"),
                Map.of("codinome", "Visão")
        ));
        return json;
    }

    public static SuperHeroXml getHeroXml(){
        final SuperHeroXml xml = new SuperHeroXml();
        xml.setCodNames(Arrays.asList("Lanterna Verde",
                "Flash",
                "Aquaman",
                "Batman",
                "Superman",
                "Mulher Maravilha")
        );
        return xml;
    }
}
