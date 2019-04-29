package com.hazard.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hazard.model.Alerta;
import com.hazard.repository.AlertaRepository;
import com.hazard.repository.UsuarioRepository;

@RestController
@RequestMapping("/alerta")
public class AlertaController {
	private AlertaRepository alertaRepository;
	private UsuarioRepository usuarioRepository;

	@Autowired
	public AlertaController(AlertaRepository alertaRepository, UsuarioRepository usuarioRepository) {
		this.alertaRepository = alertaRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Alerta> buscaAlerta(@PathVariable Long id) {
        Optional<Alerta> alertaOptional = alertaRepository.findById(id);
        return alertaOptional.isPresent() ? ResponseEntity.ok(alertaOptional.get()) :
                ResponseEntity.notFound().build();
    }
}
