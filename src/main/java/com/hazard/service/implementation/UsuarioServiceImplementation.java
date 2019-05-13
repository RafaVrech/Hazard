package com.hazard.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazard.exception.ObjetoNaoEcontratoException;
import com.hazard.model.Alerta;
import com.hazard.model.Usuario;
import com.hazard.repository.UsuarioRepository;
import com.hazard.service.UsuarioService;


@Service
public class UsuarioServiceImplementation implements UsuarioService {
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    public UsuarioServiceImplementation(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

	@Override
	public Usuario verificarLogin(String email, String senha) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmailAndSenha(email, senha);
        return usuarioOptional.orElseThrow(() ->
                new ObjetoNaoEcontratoException("Combinação de email e senha incorretos para: " + email));
	}
	@Override
	public Usuario buscaDadosUsuario(Long id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		return usuarioOptional.orElseThrow(() ->
		new ObjetoNaoEcontratoException("Não foi encontrado Usuario para: " + id));
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
        if (usuario.getAlertas() != null) 
        	 throw new ObjetoNaoEcontratoException("Utilize os end-points de criar alerta para criar alertas.");
//            for(Alerta alerta : usuario.getAlertas()) {
//            	alerta.setUsuario(Usuario.builder().id(usuario.getId()).build());
//            }
            	
        
        return usuarioRepository.save(usuario);
	}
	
	@Override
	public boolean deletarUsuario(Long id) throws Exception {
		if(id == null)
			throw new Exception();
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(!usuario.isPresent())
			throw new Exception();
		
		usuarioRepository.deleteById(id);
		
		return true;
	}
}
