package com.hazard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hazard.model.Usuario;
import com.hazard.repository.UsuarioRepository;
import com.hazard.service.UsuarioService;
import com.hazard.utils.Resposta;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;
	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
		this.usuarioService = usuarioService;
		this.usuarioRepository = usuarioRepository;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> buscaContato(@RequestParam(value = "email", required = true) String email,
												@RequestParam(value = "senha", required = true) String senha) {
		
		Usuario usuarioRetorno = usuarioService.verificarLogin(email, senha);

        return ResponseEntity.ok(new Resposta(0, "Login efetuado com sucesso", usuarioRetorno));
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> novoUsuario(@RequestBody Usuario usuario) {
		if(usuario.getId() != null)
			return ResponseEntity.badRequest().body(new Resposta(1, "Um usuário a ser inserido não pode ter um ID já definido", null));
		if(usuarioRepository.existsByEmail(usuario.getEmail()))
			return ResponseEntity.badRequest().body(new Resposta(1, "Um usuário com esse email já existe", null));
		try {
            return ResponseEntity.ok(new Resposta(0, "Usuario inserido com sucesso", usuarioService.salvarUsuario(usuario)));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().body(new Resposta(1, re.getMessage(), null));
        }
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUsuario(@PathVariable Long id)
	{
		if (id == null)
            return ResponseEntity.badRequest().body(new Resposta(1, "Falta parametro ID do usuario a ser deletado", null));
        if (!usuarioRepository.existsById(id))
            return ResponseEntity.badRequest().body(new Resposta(1, "Usuario a ser deletado não encontrado para o ID: " + id, null));
        
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok(new Resposta(0, "Usuario  " + id + " deletado com sucesso", null));
	}
}
