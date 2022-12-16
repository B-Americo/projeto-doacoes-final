package com.doacoesfinal.doacoesfinal.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_contrato")
public class ItensContrato implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
	
		@ManyToOne
		private Produto produto;
		
		@ManyToOne
		private Usuario usuario;
		
		@ManyToOne
		private Contrato contrato;
		
		private Integer quantidade=0;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Produto getProduto() {
			return produto;
		}

		public void setProduto(Produto produto) {
			this.produto = produto;
		}

		public Contrato getContrato() {
			return contrato;
		}

		public void setContrato(Contrato contrato) {
			this.contrato = contrato;
		}

		
		  public Integer getQuantidade() { return quantidade; }
		  
		  public void setQuantidade(Integer quantidade) { this.quantidade = quantidade;
		  }

		  
		  
		  
		  
		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		@Override
		public int hashCode() {
			return Objects.hash(contrato, id, produto, quantidade);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ItensContrato other = (ItensContrato) obj;
			return Objects.equals(contrato, other.contrato) && Objects.equals(id, other.id)
					&& Objects.equals(produto, other.produto) && Objects.equals(quantidade, other.quantidade);
		}
		 
		
		
}
