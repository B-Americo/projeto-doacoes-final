package com.doacoesfinal.doacoesfinal.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.doacoesfinal.doacoesfinal.repository.EstadoRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	   private String nome;
	   private String cpf;
	    private String telefone;
	   private String email;
	   private String username;
	    private String password;
	    private boolean ativo;
	    
	   // @ManyToOne(cascade= {CascadeType.ALL})
	   // @JoinColumn(name = "id_estado")
	  //  private Estado estado;
	    
	    
	    @ManyToOne(cascade= {CascadeType.ALL})
	    @JoinColumn(name = "id_endereco")
	    private Endereco endereco;
	    
	    @ManyToOne(cascade= {CascadeType.ALL})
	    @JoinColumn(name = "id_grupo")
	    private Grupo grupo;
	    
	    
	    @OneToMany(mappedBy = "usuario")
		   private List<Produto> produtos;
	    
	    
	    
	    
	    
	    
	 //   @ManyToMany
	 //   @JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "id_usuario")
	  //  ,inverseJoinColumns = @JoinColumn(name = "id_grupo"))
	 //   private List<Grupo> grupos;
	    
		/*public List<Grupo> getGrupos() {
			return grupos;
		}
		public void setGrupos(List<Grupo> grupos) {
			this.grupos = grupos;
		}*/
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
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public boolean isAtivo() {
			return ativo;
		}
		public void setAtivo(boolean ativo) {
			this.ativo = ativo;
		}	
		
		public Endereco getEndereco() {
			return endereco;
		}
		public void setEndereco(Endereco endereco) {
			this.endereco = endereco;
		}
		
		public Grupo getGrupo() {
			return grupo;
		}
		public void setGrupo(Grupo grupo) {
			this.grupo = grupo;
		}
	
		public List<Produto> getProdutos() {
			return produtos;
		}
		public void setProdutos(List<Produto> produtos) {
			this.produtos = produtos;
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
			Usuario other = (Usuario) obj;
			return Objects.equals(id, other.id);
		}
	    
	    
	
}
