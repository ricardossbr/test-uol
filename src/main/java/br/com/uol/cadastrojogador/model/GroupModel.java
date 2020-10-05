package br.com.uol.cadastrojogador.model;

import br.com.uol.cadastrojogador.enums.TeamEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode
public class GroupModel {
	private TeamEnum team;
	@Id
	private String superHero;
}