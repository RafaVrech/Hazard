package com.hazard.service;

import java.util.Optional;

import com.hazard.model.Usuario;

public interface UsuarioService {
	Optional<Usuario> verificarLogin(String usuario, String senha);
	boolean salvarUsuario(Usuario usuario);
	boolean deletarUsuario(Long id) throws Exception;
}
