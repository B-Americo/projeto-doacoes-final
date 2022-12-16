package com.doacoesfinal.doacoesfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doacoesfinal.doacoesfinal.model.Usuario;
import com.doacoesfinal.doacoesfinal.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void salvar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

}
