package com.hazard.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazard.model.Usuario;
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
	public Optional<Usuario> verificarLogin(String usuario, String senha) {
			return usuarioRepository.findByNomeAndSenha(usuario, senha);
	}

	@Override
	public boolean salvarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario) != null;
	}
}
