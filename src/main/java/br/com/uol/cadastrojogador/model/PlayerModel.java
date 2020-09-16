package br.com.uol.cadastrojogador.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Entity
@Table(name="jogador")
public class PlayerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "O campo Nome é Obrigatório.")
	private String nome;
	@NotBlank(message = "O campo E-mail é Obrigatório.")
	private String email;

	private String telefone;

	@ManyToOne(cascade=CascadeType.ALL)
	private GrupModel grupModel;
}