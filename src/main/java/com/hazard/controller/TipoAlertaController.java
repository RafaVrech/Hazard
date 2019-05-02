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

import com.hazard.model.TipoAlerta;
import com.hazard.repository.TipoAlertaRepository;
import com.hazard.utils.Resposta;

@RestController
@RequestMapping("/tipoAlerta")
public class TipoAlertaController {
	private TipoAlertaRepository tipoAlertaRepository;

	@Autowired
	public TipoAlertaController(TipoAlertaRepository tipoAlertaRepository) {
		this.tipoAlertaRepository = tipoAlertaRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> buscaTipoAlertas() {
		List<TipoAlerta> tipoAlertas = tipoAlertaRepository.findAll();
		if(tipoAlertas.isEmpty())
			return ResponseEntity.ok(new Resposta(0, "Nenhum tipo de alerta cadastrado", null));
		return ResponseEntity.ok(new Resposta(0, "Todos os tipos de alertas recuperados com sucesso", tipoAlertas));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> buscaTipoAlerta(@PathVariable Long id) {
		Optional<TipoAlerta> tipoAlertaOptional = tipoAlertaRepository.findById(id);
		return tipoAlertaOptional.isPresent() ? 
				ResponseEntity.ok(new Resposta(0, "Tipo de alerta recuperado com sucesso", ResponseEntity.ok(tipoAlertaOptional.get()))) :
				ResponseEntity.badRequest().body(new Resposta(1, "Não foi encontrado tipo de alerta alerta com ID: " + id, null));
	}

	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> novoTipoAlerta(@RequestBody TipoAlerta tipoAlerta) {
        TipoAlerta savedTipoAlerta = tipoAlertaRepository.save(tipoAlerta);
        if(savedTipoAlerta != null)
        		return ResponseEntity.ok(new Resposta(0, "Tipo de alerta salvo com sucesso", savedTipoAlerta));
    		else		
    			return ResponseEntity.badRequest().body(new Resposta(1, "Não foi possível salvar o tipo de alerta", null));
    }

	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<Object> updateTipoAlerta(@RequestBody TipoAlerta tipoAlerta) {
        if (tipoAlerta == null || tipoAlerta.getId() == null)
            return ResponseEntity.badRequest().body(new Resposta(1, "Tipo de alerta não preenchido", null));
        if (!tipoAlertaRepository.existsById(tipoAlerta.getId()))
            return ResponseEntity.badRequest().body(new Resposta(1, "Não foi encontrado tipo de alerta com ID: " + tipoAlerta.getId(), null));

        TipoAlerta tipoAlertaUpdated = tipoAlertaRepository.save(tipoAlerta);
        return ResponseEntity.ok(new Resposta(0, "Tipo de alerta alterado com sucesso", tipoAlertaUpdated));
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTipoAlerta(@PathVariable Long id) {
        if (id == null)
            return ResponseEntity.badRequest().body(new Resposta(1, "Falta parametro ID do tipo de alerta a ser deletado", null));
        if (!tipoAlertaRepository.existsById(id))
            return ResponseEntity.badRequest().body(new Resposta(1, "Tipo de alerta a ser deletado não encontrado para o ID: " + id, null));

        tipoAlertaRepository.deleteById(id);
        return ResponseEntity.ok(new Resposta(0, "Tipo de alerta " + id + " deletado com sucesso", null));
    }
}
