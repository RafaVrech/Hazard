package com.hazard.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hazard.model.Usuario;
import com.hazard.service.UsuarioService;
import com.hazard.utils.Resposta;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> buscaContato(@RequestParam(value = "usuario", required = true) String usuario,
												@RequestParam(value = "senha", required = true) String senha) {
		
		Optional<Usuario> usuarioBD = usuarioService.verificarLogin(usuario, senha);
		return usuarioBD.isPresent() ? ResponseEntity.ok(new Resposta(0, "", usuarioBD)) :
									   ResponseEntity.badRequest().build();
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> novoUsuario(@RequestBody Usuario usuario) {
		try {
            return ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().build();
        }
    }
	
	
}
