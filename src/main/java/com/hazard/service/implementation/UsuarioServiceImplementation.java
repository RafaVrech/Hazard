package com.hazard.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazard.model.Alerta;
import com.hazard.model.Usuario;
import com.hazard.repository.UsuarioRepository;
import com.hazard.service.UsuarioService;
import com.ibm.agenda.exception.ObjetoNaoEcontratoException;


@Service
public class UsuarioServiceImplementation implements UsuarioService{
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    public UsuarioServiceImplementation(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

//	@Override
//	public Optional<Usuario> verificarLogin(String usuario, String senha) {
//			return usuarioRepository.findByNomeAndSenha(usuario, senha);
//	}
	@Override
	public Usuario verificarLogin(String usuario, String senha) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeAndSenha(usuario, senha);
        return usuarioOptional.orElseThrow(() ->
                new ObjetoNaoEcontratoException("Combinação de usuario e senha incorretos para: " + usuario));
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
        if (usuario.getAlertas() != null) 
            for(Alerta alerta : usuario.getAlertas()) 
            	alerta.setUsuario(usuario);
        
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
