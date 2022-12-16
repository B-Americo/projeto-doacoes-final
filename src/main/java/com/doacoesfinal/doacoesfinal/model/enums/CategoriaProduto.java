package com.doacoesfinal.doacoesfinal.model.enums;

public enum CategoriaProduto {
	
	ALIMENTOS("Alimentos"),
	ELETRODOMESTICOS("Eletrodomésticos"),
	ELETRONICOS("Eletrônicos"),
	LIVROS("Livros"),
	ROUPAS("Roupas");
	
	private String descricao;

	CategoriaProduto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
