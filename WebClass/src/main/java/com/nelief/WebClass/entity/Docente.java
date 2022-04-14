package com.nelief.WebClass.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "docenti")
public class Docente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cognome;
	private String materia;
	private String riferimento;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
	private Utente utente;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "Docente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", materia=" + materia
				+ ", riferimento=" + riferimento + ", utente=" + utente + "]";
	}

	public Docente(long id, String nome, String cognome, String materia, String riferimento, Utente utente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.materia = materia;
		this.riferimento = riferimento;
		this.utente = utente;
	}

	public Docente() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
}
