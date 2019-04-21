package com.hazard.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazard.repository.UsuarioRepository;
import com.hazard.service.UsuarioService;


@Service
public class UsuarioServiceImplementation implements UsuarioService{
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    public UsuarioServiceImplementation(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

	@Override
	public boolean verificarLogin(String usuario, String senha) {
		if(usuarioRepository.findByNomeAndSenha(usuario, senha) != null)
			return true;
		return false;
	}
}
