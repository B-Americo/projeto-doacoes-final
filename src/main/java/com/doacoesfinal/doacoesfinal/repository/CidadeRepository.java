package com.doacoesfinal.doacoesfinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.doacoesfinal.doacoesfinal.model.Cidade;

@Repository
@Transactional
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	public List<Cidade> findByEstadoId(Long id);

}
