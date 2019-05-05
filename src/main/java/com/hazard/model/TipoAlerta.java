package com.hazard.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

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
    
//    @ManyToOne(targetEntity = Alerta.class)
//    private List<Alerta> alertas;
}
