package com.doacoesfinal.doacoesfinal.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupo")
public class Grupo implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String nome;
		
		@OneToMany(mappedBy = "grupo")
		private List<Usuario> usuarios;
		
		
		
		
		@ManyToMany
		@JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "id_grupo")
	    ,inverseJoinColumns = @JoinColumn(name = "id_permissao"))
		private List<Permissao> permissoes;
					
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public List<Permissao> getPermissoes() {
			return permissoes;
		}
		public void setPermissoes(List<Permissao> permissoes) {
			this.permissoes = permissoes;
		}
		public List<Usuario> getUsuarios() {
			return usuarios;
		}
		public void setUsuarios(List<Usuario> usuarios) {
			this.usuarios = usuarios;
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
			Grupo other = (Grupo) obj;
			return Objects.equals(id, other.id);
		}
		
		
		
}
