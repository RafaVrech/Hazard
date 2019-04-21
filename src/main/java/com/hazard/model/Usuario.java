package com.hazard.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue(
			generator="seq_usuario",
			strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(
			allocationSize = 1,
			name="seq_usuario",
			sequenceName = "seq_usuario")
	private Long id;
	
	private String nome;
	private String email;
	private String senha;
	
	@OneToMany(
			mappedBy = "usuario", 
			targetEntity = Alerta.class,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Alerta> alertas;
}
