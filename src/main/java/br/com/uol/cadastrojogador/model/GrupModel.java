package br.com.uol.cadastrojogador.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="grupo")
public class GrupModel {
	@Id
	private String codNome;
	private String nomeGrupo;
}