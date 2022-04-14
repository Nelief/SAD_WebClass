package com.nelief.WebClass.entity;

import org.springframework.stereotype.Component;


@Component
public class DocenteDTO {
	private String nome;
	private String cognome;
	private String materia;
	private String riferimento;
	private String username;
	private String password;
	private String email;
	
	public DocenteDTO(String nome, String cognome, String materia, String riferimento, String username, String password,
			String email) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.materia = materia;
		this.riferimento = riferimento;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public DocenteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DocenteDTO [nome=" + nome + ", cognome=" + cognome + ", materia=" + materia + ", riferimento="
				+ riferimento + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getRiferimento() {
		return riferimento;
	}
	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

/*
* DocenteDTO implementa un Data Tranfer Object pattern, lo usiamo per ridurre ad un semplice passaggio di 1 oggetto 
* la comunicazione dei dati Docente ed Utente in fase di registrazione, il DTO viene poi suddiviso in 2 oggetti figli dal service
*/