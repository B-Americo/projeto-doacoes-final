package com.doacoesfinal.doacoesfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.doacoesfinal.doacoesfinal.model.ItensContrato;
import com.doacoesfinal.doacoesfinal.model.Usuario;

@Repository
@Transactional
public interface ItensContratoRepository extends JpaRepository<ItensContrato, Long>{

}
