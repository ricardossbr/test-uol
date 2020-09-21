package br.com.uol.cadastrojogador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PlayerModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	@Column(name = "phone_number")
	private String phoneNumber;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "group_id")
	private GroupModel groupModel;
}