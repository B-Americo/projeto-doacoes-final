package com.doacoesfinal.doacoesfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doacoesfinal.doacoesfinal.model.Produto;
import com.doacoesfinal.doacoesfinal.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void salvar(Produto produto) {
		produtoRepository.save(produto);
	}
	
	
	
}
