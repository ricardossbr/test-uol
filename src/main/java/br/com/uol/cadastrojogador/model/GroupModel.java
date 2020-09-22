package br.com.uol.cadastrojogador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class GroupModel {
	private String groupName;
	@Id
	private String name;
}