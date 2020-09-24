package br.com.uol.cadastrojogador.cadastrojogador.builder;

import br.com.uol.cadastrojogador.model.GroupModel;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GroupTestBuilder {


    public static GroupModel getGroupModel(){
        final int element = new Random().nextInt(getList().size());
        return getList().get(element);
    }

    public static List<GroupModel> getList(){
        return Arrays.asList(
                new GroupModel("VINGADORES", "Capitão América"),
                new GroupModel("VINGADORES", "Hulk"),
                new GroupModel("VINGADORES", "Pantera Negra"),
                new GroupModel("VINGADORES", "Homem de Ferro"),
                new GroupModel("VINGADORES", "Thor"),
                new GroupModel("VINGADORES", "Feiticeira Escarlate"),
                new GroupModel("VINGADORES", "Visão"),
                new GroupModel("LIGADAJUSTICA", "Lanterna Verde"),
                new GroupModel("LIGADAJUSTICA", "Flash"),
                new GroupModel("LIGADAJUSTICA", "Aquaman"),
                new GroupModel("LIGADAJUSTICA", "Batman"),
                new GroupModel("LIGADAJUSTICA", "Superman"),
                new GroupModel("LIGADAJUSTICA", "Mulher Maravilha"));
    }

    public static List<GroupModel> getListVingadores(){
        return Arrays.asList(
                new GroupModel("VINGADORES", "Capitão América"),
                new GroupModel("VINGADORES", "Capitão América"),
                new GroupModel("VINGADORES", "Capitão América"),
                new GroupModel("VINGADORES", "Hulk"),
                new GroupModel("VINGADORES", "Hulk"),
                new GroupModel("VINGADORES", "Hulk"),
                new GroupModel("VINGADORES", "Pantera Negra"),
                new GroupModel("VINGADORES", "Pantera Negra"),
                new GroupModel("VINGADORES", "Pantera Negra"),
                new GroupModel("VINGADORES", "Homem de Ferro"),
                new GroupModel("VINGADORES", "Homem de Ferro"),
                new GroupModel("VINGADORES", "Homem de Ferro"),
                new GroupModel("VINGADORES", "Thor"),
                new GroupModel("VINGADORES", "Thor"),
                new GroupModel("VINGADORES", "Thor"),
                new GroupModel("VINGADORES", "Feiticeira Escarlate"),
                new GroupModel("VINGADORES", "Feiticeira Escarlate"),
                new GroupModel("VINGADORES", "Feiticeira Escarlate"),
                new GroupModel("VINGADORES", "Visão"),
                new GroupModel("VINGADORES", "Visão"),
                new GroupModel("VINGADORES", "Visão"));
    }

    public static List<GroupModel> getListLigaDaJustica(){
        return Arrays.asList(
                new GroupModel("LIGADAJUSTICA", "Lanterna Verde"),
                new GroupModel("LIGADAJUSTICA", "Lanterna Verde"),
                new GroupModel("LIGADAJUSTICA", "Lanterna Verde"),
                new GroupModel("LIGADAJUSTICA", "Flash"),
                new GroupModel("LIGADAJUSTICA", "Flash"),
                new GroupModel("LIGADAJUSTICA", "Flash"),
                new GroupModel("LIGADAJUSTICA", "Aquaman"),
                new GroupModel("LIGADAJUSTICA", "Aquaman"),
                new GroupModel("LIGADAJUSTICA", "Aquaman"),
                new GroupModel("LIGADAJUSTICA", "Batman"),
                new GroupModel("LIGADAJUSTICA", "Batman"),
                new GroupModel("LIGADAJUSTICA", "Batman"),
                new GroupModel("LIGADAJUSTICA", "Superman"),
                new GroupModel("LIGADAJUSTICA", "Superman"),
                new GroupModel("LIGADAJUSTICA", "Superman"),
                new GroupModel("LIGADAJUSTICA", "Mulher Maravilha"),
                new GroupModel("LIGADAJUSTICA", "Mulher Maravilha"),
                new GroupModel("LIGADAJUSTICA", "Mulher Maravilha"));
    }

    public static List<GroupModel> getListEmpty(){
        return Arrays.asList();
    }
}
