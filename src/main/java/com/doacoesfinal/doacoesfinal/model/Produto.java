package com.doacoesfinal.doacoesfinal.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import com.doacoesfinal.doacoesfinal.model.enums.CategoriaProduto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "produto")
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
    //@NotBlank(message = "Nome é obrigatório")
    private String nome;
    
   //  @Size(min=1, max=50, message = "o tamanho da descrição deve está entre 1 - 50")
     private String descricao;
     
   //  @NotBlank(message = "condicão é obrigatório")
     private String condicao;
     
     private Integer quantidade;
     
     @Lob
     private byte[] imagens;
     
     @Temporal(TemporalType.TIMESTAMP)
     private Calendar dataDonate;
     
     @Enumerated(EnumType.STRING)
     private CategoriaProduto categoriaProduto;
     
     
     @ManyToOne(cascade= {CascadeType.MERGE})
	    @JoinColumn(name = "id_usuario")
	    private Usuario usuario;
     

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImagens() {
		return imagens;
	}

	public void setImagens(byte[] imagens) {
		this.imagens = imagens;
	}

	public CategoriaProduto getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Calendar getDataDonate() {
		return dataDonate;
	}

	public void setDataDonate(Calendar dataDonate) {
		this.dataDonate = dataDonate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
    
    
}