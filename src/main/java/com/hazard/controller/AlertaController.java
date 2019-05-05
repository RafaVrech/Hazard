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
import com.hazard.repository.TipoAlertaRepository;
import com.hazard.repository.UsuarioRepository;
import com.hazard.service.AlertaService;
import com.hazard.utils.Resposta;

@RestController
@RequestMapping("/alerta")
public class AlertaController {
	private AlertaRepository alertaRepository;
	private AlertaService alertaService;
	private UsuarioRepository usuarioRepository;
	private TipoAlertaRepository tipoAlertaRepository;

	@Autowired
	public AlertaController(AlertaRepository alertaRepository, UsuarioRepository usuarioRepository, TipoAlertaRepository tipoAlertaRepository, AlertaService alertaService) {
		this.alertaRepository = alertaRepository;
		this.usuarioRepository = usuarioRepository;
		this.tipoAlertaRepository = tipoAlertaRepository;
		this.alertaService = alertaService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> buscaAlertas() {
		List<Alerta> alertas = alertaRepository.findAll();
		if(alertas.isEmpty())
			return ResponseEntity.ok(new Resposta(0, "Nenhum alerta cadastrado", null));
		return ResponseEntity.ok(new Resposta(0, "Todos os alertas recuperados com sucesso", alertas));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> buscaAlerta(@PathVariable Long id) {
		Optional<Alerta> alertaOptional = alertaRepository.findById(id);
		return alertaOptional.isPresent() ? 
				ResponseEntity.ok(new Resposta(0, "Alerta recuperado com sucesso", alertaOptional.get())) :
				ResponseEntity.badRequest().body(new Resposta(1, "Não foi encontrado alerta com ID: " + id, null));
	}

	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> novoAlerta(@RequestBody Alerta alerta) {
        if (alerta.getUsuario() == null || alerta.getUsuario().getId() == null)
            return ResponseEntity.badRequest().body(new Resposta(1, "Usuario não preenchido", null));
        if (!usuarioRepository.existsById(alerta.getUsuario().getId()))
            return ResponseEntity.badRequest().body(new Resposta(1, "Não foi encontrado usuário com ID: " + alerta.getUsuario().getId(), null));
        
        if (alerta.getTipoAlerta() == null || alerta.getTipoAlerta().getId() == null)
            return ResponseEntity.badRequest().body(new Resposta(1, "Tipo alerta não preenchido", null));
        if (!tipoAlertaRepository.existsById(alerta.getTipoAlerta().getId()))
            return ResponseEntity.badRequest().body(new Resposta(1, "Não foi encontrado tipoAlerta com ID: " + alerta.getTipoAlerta().getId(), null));
        
		return ResponseEntity.ok(new Resposta(0, "Alerta salvo com sucesso", alertaService.salvarAlerta(alerta)));
    }

	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<Object> updateAlerta(@RequestBody Alerta alerta) {
        if (alerta == null || alerta.getId() == null)
            return ResponseEntity.badRequest().body(new Resposta(1, "Alerta não preenchido", null));
        if (!alertaRepository.existsById(alerta.getId()))
            return ResponseEntity.badRequest().body(new Resposta(1, "Não foi encontrado alerta com ID: " + alerta.getId(), null));

        Alerta alertaUpdated = alertaRepository.save(alerta);
        return ResponseEntity.ok(new Resposta(0, "Alerta alterado com sucesso", alertaUpdated));
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAlerta(@PathVariable Long id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new Resposta(1, "Falta parametro ID do alerta a ser deletado", null));
        if (!alertaRepository.existsById(id))
            return ResponseEntity.badRequest().body(new Resposta(1, "Alerta a ser deletado não encontrado para o ID: " + id, null));

        alertaRepository.deleteById(id);
        return ResponseEntity.ok(new Resposta(0, "Alerta " + id + " deletado com sucesso", null));
    }
}
