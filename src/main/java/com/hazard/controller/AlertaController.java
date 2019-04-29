package com.hazard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hazard.model.Alerta;
import com.hazard.repository.AlertaRepository;
import com.hazard.repository.UsuarioRepository;
import com.ibm.agenda.model.Telefone;

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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Alerta>> buscaAlertas() {
		return ResponseEntity.ok(alertaRepository.findAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Alerta> buscaAlerta(@PathVariable Long id) {
		Optional<Alerta> alertaOptional = alertaRepository.findById(id);
		return alertaOptional.isPresent() ? ResponseEntity.ok(alertaOptional.get()) : ResponseEntity.notFound().build();
	}

	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Alerta> novoTelefone(@RequestBody Alerta alerta) {
        if (alerta.getUsuario() == null || alerta.getUsuario().getId() == null)
            return ResponseEntity.badRequest().build();
        if (!usuarioRepository.existsById(alerta.getUsuario().getId()))
            return ResponseEntity.notFound().build();
        Alerta savedAlerta = alertaRepository.save(alerta);
        return ResponseEntity.ok(savedAlerta);
    }

	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<Alerta> updateAlerta(@RequestBody Alerta alerta) {
        if (alerta == null || alerta.getId() == null)
            return ResponseEntity.badRequest().build();
        if (!alertaRepository.existsById(alerta.getId()))
            return ResponseEntity.notFound().build();

        Alerta alertaUpdated = alertaRepository.save(alerta);
        return ResponseEntity.ok(alertaUpdated);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAlerta(@PathVariable Long id) {
        if (id == null)
            return ResponseEntity.badRequest().build();
        if (!alertaRepository.existsById(id))
            return ResponseEntity.notFound().build();

        alertaRepository.deleteById(id);
        return ResponseEntity.ok("Telefone removido com sucesso!");
    }
}
