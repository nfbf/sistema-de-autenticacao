package br.edu.unibratec.autenticacao.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Usuario {

	@Id
	@Column(name = "id",nullable=false, length=30, unique=true)
	private String login; // Login
	private String senha; // Senha
	private String situacao; // Bloqueado ou Desbloqueado
	private String status; // Ativo ou Invativo
	private String perfil; // Regular ou Administrador
	//mapeia uma relação um para um com Pessoa, usando o Id como ForeignKey
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL,fetch = FetchType.EAGER, optional = false)
	private Pessoa pessoa;
	
	public Usuario() {
		 
	}
	
	public Usuario(String login, String senha, String situacao, String status, String perfil) {
		super();
		this.login = login;
		this.senha = senha;
		this.situacao = situacao;
		this.status = status;
		this.perfil = perfil;
	}



	public Usuario (String username, String senha) {
		this.login = username;
		this.senha = senha;
		
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


}
