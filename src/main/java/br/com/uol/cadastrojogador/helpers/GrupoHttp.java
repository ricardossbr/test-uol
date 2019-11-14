package br.com.uol.cadastrojogador.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GrupoHttp {
	final private List<String> codNomeVingadores = new ArrayList<String>();
	final private List<String> codNomesLiga = new ArrayList<String>();
	
	public GrupoHttp() {
	}
	
	public List<String> getCodNomeVingadores() {
		return Collections.unmodifiableList(codNomeVingadores);
	}
	
	public List<String> getCodNomesLiga() {
		return Collections.unmodifiableList(codNomesLiga);
	}
	
	public void addVingadores(String to) {
		this.codNomeVingadores.add(to);
	}
	public void addLiga(String to) {
		this.codNomesLiga.add(to);
	}
}
