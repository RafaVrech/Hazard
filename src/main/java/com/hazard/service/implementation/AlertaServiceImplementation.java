package com.hazard.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazard.exception.ObjetoNaoEcontratoException;
import com.hazard.model.Alerta;
import com.hazard.repository.AlertaRepository;
import com.hazard.repository.TipoAlertaRepository;
import com.hazard.repository.UsuarioRepository;
import com.hazard.service.AlertaService;


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
	public Alerta salvarAlerta(Alerta alerta) {
		
		//Verificaçao por segurança, no controller eu já vi se o usuario existe
		alerta.setUsuario(usuarioRepository.findById(alerta.getUsuario().getId()).orElseThrow(() ->
                new ObjetoNaoEcontratoException("Usuario não encontrado: " + alerta.getUsuario().getId())));
		//Verificaçao por segurança, no controller eu já vi se o tipoAlerta existe
		alerta.setTipoAlerta(tipoAlertaRepository.findById(alerta.getTipoAlerta().getId()).orElseThrow(() ->
                new ObjetoNaoEcontratoException("TipoAlerta não encontrado: " + alerta.getTipoAlerta().getId())));
		
		alerta.getUsuario().setAlertas(null);
		//Cortando loop infinito
//		for(Alerta alertaDentroDoUsuario : alerta.getUsuario().getAlertas())
//			alertaDentroDoUsuario.setUsuario(null);
		
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
