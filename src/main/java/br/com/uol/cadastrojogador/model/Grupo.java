package br.com.uol.cadastrojogador.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="grupo")
public class Grupo {
	@Id
	private String codNome;
	private String nomeGrupo;
	
	public Grupo () {
		
	}

	public String getCodNome() {
		return codNome;
	}
	public void setCodNome(String codNome) {
		this.codNome = codNome;
	}
	public String getNomeGrupo() {
		return nomeGrupo;
	}
	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}
}