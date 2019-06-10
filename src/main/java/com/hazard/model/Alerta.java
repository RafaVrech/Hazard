package com.hazard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude="usuario")
public class Alerta {
		@Id
		@GeneratedValue(
				generator="seq_alerta", 
				strategy=GenerationType.SEQUENCE)
		@SequenceGenerator(
				allocationSize = 1,
				name="seq_alerta",
				sequenceName = "seq_alerta")
	    private Long id;
		
		@Column(precision=10, scale=2)
	    private double latitude;
		@Column(precision=10, scale=2)
	    private double longitude;
	    private String nome;
	    private String descricao;
	    private Date criacao;
	    
	    
	    @ManyToOne
	    private Usuario usuario;

	    @ManyToOne
	    private TipoAlerta tipoAlerta;
}
