package com.nelief.WebClass.entity;

public class GenitoreDTO {
	
	private String nome;
	private String cognome;
	private String riferimento;
	private long id_figlio;
	
	private String username;
	private String password;
	private String email;
	
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
	public String getRiferimento() {
		return riferimento;
	}
	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}
	public long getId_figlio() {
		return id_figlio;
	}
	public void setId_figlio(long id_figlio) {
		this.id_figlio = id_figlio;
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
	public GenitoreDTO(String nome, String cognome, String riferimento, long id_figlio, String username,
			String password, String email) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.riferimento = riferimento;
		this.id_figlio = id_figlio;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public GenitoreDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "GenitoreDTO [nome=" + nome + ", cognome=" + cognome + ", riferimento=" + riferimento + ", id_figlio="
				+ id_figlio + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
	
}
