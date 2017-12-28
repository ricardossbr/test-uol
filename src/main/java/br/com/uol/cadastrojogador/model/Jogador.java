package br.com.uol.cadastrojogador.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="jogador")
public class Jogador {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 50)
	@NotBlank(message = "O campo Nome é Obrigatório.")
	private String nome;
	@Column(nullable = false, length = 40)
	@NotBlank(message = "O campo E-mail é Obrigatório.")
	private String email;
	private String telefone;
	@ManyToOne(cascade=CascadeType.ALL)
	private Grupo grupo;
	
	
	public Jogador() {
		
	}
	public Jogador(String nome, String email, String telefone, Grupo grupo) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.grupo = grupo;
	}
	//GET's and SET's
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
}