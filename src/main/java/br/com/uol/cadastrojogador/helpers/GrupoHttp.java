package br.com.uol.cadastrojogador.helpers;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GrupoHttp {
	private List<String> codNomeVingadores;
	private List<String> codNomesLiga;
	
	public GrupoHttp() {
		
	}
	
	public GrupoHttp(List<String> codNomeVingadores, List<String> codNomesLiga) {
		this.codNomeVingadores = codNomeVingadores;
		this.codNomesLiga = codNomesLiga;
	}
	
	
	public List<String> getCodNomeVingadores() {
		return codNomeVingadores;
	}
	public void setCodNomeVingadores(List<String> codNomeVingadores) {
		this.codNomeVingadores = codNomeVingadores;
	}
	public List<String> getCodNomesLiga() {
		return codNomesLiga;
	}
	public void setCodNomesLiga(List<String> codNomesLiga) {
		this.codNomesLiga = codNomesLiga;
	}
	public void addVingadores(String to) {
		this.codNomeVingadores.add(to);
	}
	public void addLiga(String to) {
		this.codNomesLiga.add(to);
	}
}
