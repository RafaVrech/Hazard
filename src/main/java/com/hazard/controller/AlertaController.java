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

	@RequestMapping(method = RequestMethod.POST) // Req Param padrão jà è json
	public ResponseEntity<Alerta> novoAlerta(@RequestBody Alerta alerta) {
		System.out.println(alerta.getUsuario());
		if (alerta != null && alerta.getUsuario() != null && alerta.getUsuario().getId() != null
				&& usuarioRepository.existsById(alerta.getUsuario().getId()))
			return ResponseEntity.ok(alertaRepository.save(alerta));

		return ResponseEntity.notFound().build();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Alerta> updateAlerta(@RequestBody Alerta alerta) {
		if (alerta != null) {
			return ResponseEntity.ok(alertaRepository.save(alerta));
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteAlerta(@PathVariable Long id) {
		if (id != null) {
			alertaRepository.deleteById(id);
			return "Deletado com sucesso";
		}
		return "Alerta não encontrado";
	}
}
