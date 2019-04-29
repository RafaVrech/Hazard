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
		
		Usuario usuarioRetorno = usuarioService.verificarLogin(usuario, senha);

        return ResponseEntity.ok(new Resposta(0, "", usuarioRetorno));
	}
	
	@RequestMapping(method = RequestMethod.POST)//TA SUBSTITUINDO QUANDO MANDA COM ID JA EXISTENTE
    public ResponseEntity<Object> novoUsuario(@RequestBody Usuario usuario) {
		try {
            return ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().build();
        }
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUsuario(@PathVariable Long id)
	{
		try {
			return usuarioService.deletarUsuario(id) ? "Deletado com sucesso" : "Usuario não encontrado";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "erro";
	}
}
