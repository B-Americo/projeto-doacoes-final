package com.doacoesfinal.doacoesfinal.security;

import java.lang.StackWalker.Option;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doacoesfinal.doacoesfinal.config.UsuarioSistema;
import com.doacoesfinal.doacoesfinal.model.Usuario;
import com.doacoesfinal.doacoesfinal.repository.UsuarioRepository;

import jakarta.transaction.Transactional;


@Service
public class AppUserDetailsService implements UserDetailsService{

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	
	//public AppUserDetailsService(UsuarioRepository usuarioRepository) {
	//	this.usuarioRepository = usuarioRepository;
	//}


	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha incorretos"));			
		     
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}




	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		
		//Lista de permissões do usuário
		
		List<String> permissoes = usuarioRepository.permissoes(usuario);
		permissoes.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.toUpperCase())));
		
		
		return authorities;
	}

}