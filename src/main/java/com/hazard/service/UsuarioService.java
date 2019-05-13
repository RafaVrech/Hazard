package com.hazard.service;

import java.util.Optional;

import com.hazard.model.Usuario;

public interface UsuarioService {
	Usuario verificarLogin(String usuario, String senha);
	Usuario salvarUsuario(Usuario usuario);
	boolean deletarUsuario(Long id) throws Exception;
	Usuario buscaDadosUsuario(Long id);
}
