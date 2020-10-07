package br.com.uol.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class SuperHeroJson {
    @JsonProperty("vingadores")
    private List<Map<String, String>> vingadores;
}
