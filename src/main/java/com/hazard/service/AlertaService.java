package com.hazard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.hazard.model.Alerta;

public interface AlertaService {

	Alerta salvarAlerta(Alerta alerta);

	List<Alerta> buscarAlertas();

	ResponseEntity<Object> buscarAlerta(long id);
}
