package com.doacoesfinal.doacoesfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.doacoesfinal.doacoesfinal.model.Contrato;

@Repository
@Transactional
public interface ContratoRepository extends JpaRepository<Contrato, Long>{

}
