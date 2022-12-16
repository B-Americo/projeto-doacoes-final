package com.doacoesfinal.doacoesfinal.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.doacoesfinal.doacoesfinal.config.UsuarioSistema;
import com.doacoesfinal.doacoesfinal.model.Produto;
import com.doacoesfinal.doacoesfinal.model.Usuario;

@Repository
@Transactional
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public Optional<Produto> findByNome(String nome);
	
	@Query("select p from Produto p where p.nome like %?1%")
	List<Produto> findProdutoByNome(String nome);
	
	@Query("select p from Produto p inner join p.usuario u where u.username = ?1 and p.nome like %?2%")
	List<Produto> findProdutoByUsuarioLogadoFiltroNome(String usuarioLogadoDonate, String filtrarDonatesUsuario);
	
	
	@Query("select p from Produto p inner join p.usuario u where u.username = ?1")
	List<Produto> findProdutoByUsuarioLogado(String usuarioLogadoDonate);
	
	
	@Query("select p from Produto p inner join p.usuario u inner join u.endereco e")
	List<Produto> findProdutoByUsuarioandItem();
		
	@Query("select p from Produto p inner join p.usuario u inner join u.endereco e where p.nome like %?1%")
	List<Produto> findProdutoPesquisarByNome(String nome);
}
