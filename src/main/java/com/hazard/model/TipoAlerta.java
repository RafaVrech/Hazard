package com.hazard.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class TipoAlerta {
	@Id
	@GeneratedValue(
			generator="seq_tipo_alerta", 
			strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(
			allocationSize = 1,
			name="seq_tipo_alerta",
			sequenceName = "seq_tipo_alerta")
    private Long id;
	@NotNull
    private int gravidade;
	@NotNull
	private String nome;
	@NotNull
	private long raio;
	@NotNull
	private Date expira;
    
	@OneToMany(
			mappedBy = "tipoAlerta", 
			targetEntity = Alerta.class,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
    private List<Alerta> alertas;
}
