package br.edu.unibratec.autenticacao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Pessoa {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id ;
	private String nome;
	@Column(unique=true)
	private String email;
	private String phone;
	@OneToOne()
	@JoinColumn(name = "user_ID")
    private Usuario usuario;

	
	public Pessoa() {
	}
	
	public Pessoa(String nome, String email, String phone, Usuario usuario) {
		super();
		this.nome = nome;
		this.email = email;
		this.phone = phone;
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	} 
	
	

	
}
