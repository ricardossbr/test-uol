package br.com.uol.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class SuperHeroXml {
    @JsonProperty("codinomes")
    private List<String> codNames;
}
