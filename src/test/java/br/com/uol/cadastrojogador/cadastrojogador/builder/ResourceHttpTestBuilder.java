package br.com.uol.cadastrojogador.cadastrojogador.builder;

import br.com.uol.cadastrojogador.dto.SuperHeroJson;
import br.com.uol.cadastrojogador.dto.SuperHeroXml;

import java.util.Arrays;
import java.util.Map;

public class ResourceHttpTestBuilder {

    public static SuperHeroJson getHeroJson(){
        final SuperHeroJson json = new SuperHeroJson();
        json.setVingadores(Arrays.asList(
                Map.of("codinome", "Hulk"),
                Map.of("codinome", "Capitão América"),
                Map.of("codinome", "Pantera Negra"),
                Map.of("codinome", "Homem de Ferro"),
                Map.of("codinome", "Thor"),
                Map.of("codinome", "Feiticeira Escarlate"),
                Map.of("codinome", "Visão")));
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
