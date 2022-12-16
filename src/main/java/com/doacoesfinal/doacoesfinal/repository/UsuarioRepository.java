package com.doacoesfinal.doacoesfinal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.doacoesfinal.doacoesfinal.model.Usuario;


@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
		
	public Optional <Usuario> findByUsername(String username); //procura por login no banco
	
	
	@Query("select distinct p.nome from Usuario u inner join u.grupo g inner join g.permissoes p where u = :usuario")
	public List<String> permissoes(Usuario usuario);
	
	@Query("from Usuario where username=?1")
	public List<Usuario> buscarUsuarioUsername(String username);
	
}
