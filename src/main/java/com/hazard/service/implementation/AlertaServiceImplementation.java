package com.hazard.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hazard.exception.ObjetoNaoEcontratoException;
import com.hazard.model.Alerta;
import com.hazard.repository.AlertaRepository;
import com.hazard.repository.TipoAlertaRepository;
import com.hazard.repository.UsuarioRepository;
import com.hazard.service.AlertaService;
import com.hazard.utils.Resposta;


@Service
public class AlertaServiceImplementation implements AlertaService {
	private AlertaRepository alertaRepository;
	private UsuarioRepository usuarioRepository;
	private TipoAlertaRepository tipoAlertaRepository;
	
	@Autowired
    public AlertaServiceImplementation(AlertaRepository alertaRepository, UsuarioRepository usuarioRepository, TipoAlertaRepository tipoAlertaRepository) {
        this.alertaRepository = alertaRepository;
        this.usuarioRepository = usuarioRepository;
        this.tipoAlertaRepository = tipoAlertaRepository;
    }

	@Override
	public List<Alerta> buscarAlertas() {
		List<Alerta> alertas = alertaRepository.findAll();
		alertas.forEach(x -> {
			x.setUsuario(null);
			x.getTipoAlerta().setAlertas(null);
		});
		
		return alertas;
	}
	@Override
	public ResponseEntity<Object> buscarAlerta(long id) {
		Optional<Alerta> alertaOptional = alertaRepository.findById(id);
		
		if(!alertaOptional.isPresent()) 
			return ResponseEntity.badRequest().body(new Resposta(1, "Não foi encontrado alerta com ID: " + id, null));
		Alerta alerta = alertaOptional.get();
		
		alerta.getTipoAlerta().setAlertas(null);
		alerta.getUsuario().setAlertas(null);
		return ResponseEntity.ok(new Resposta(0, "Alerta recuperado com sucesso", alerta));
	}
	
	@Override
	public Alerta salvarAlerta(Alerta alerta) {
		
		//Verificaçao por segurança, no controller eu já vi se o usuario existe
		alerta.setUsuario(usuarioRepository.findById(alerta.getUsuario().getId()).orElseThrow(() ->
                new ObjetoNaoEcontratoException("Usuario não encontrado: " + alerta.getUsuario().getId())));
		//Verificaçao por segurança, no controller eu já vi se o tipoAlerta existe
		alerta.setTipoAlerta(tipoAlertaRepository.findById(alerta.getTipoAlerta().getId()).orElseThrow(() ->
                new ObjetoNaoEcontratoException("TipoAlerta não encontrado: " + alerta.getTipoAlerta().getId())));
		
		alerta.getUsuario().setAlertas(null);
		alerta.getTipoAlerta().setAlertas(null);
		
		return alertaRepository.save(alerta);
	}
	
//	@Override
//	public boolean deletarUsuario(Long id) throws Exception {
//		if(id == null)
//			throw new Exception();
//		
//		Optional<Usuario> usuario = usuarioRepository.findById(id);
//		if(!usuario.isPresent())
//			throw new Exception();
//		
//		usuarioRepository.deleteById(id);
//		
//		return true;
//	}
}
