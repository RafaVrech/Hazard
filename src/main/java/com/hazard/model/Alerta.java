package com.hazard.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.hazard.model.TipoAlerta;
import com.hazard.model.Usuario;

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
		
	    private long latitude;
	    private long longitude;
	    private String nome;
	    private Date criacao;
	    
	    @ManyToOne(targetEntity = Usuario.class)
	    private Usuario usuario;

	    @ManyToOne(targetEntity = TipoAlerta.class)
	    private TipoAlerta tipoAlerta;
}
