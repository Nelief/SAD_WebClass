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
@Table(name = "genitori")
public class Genitore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String cognome;
	private String riferimento;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
	private Utente utente;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "figlio_id", referencedColumnName = "id")
	private Studente figlio;
	
	
	
	public Studente getFiglio() {
		return figlio;
	}

	public void setFiglio(Studente figlio) {
		this.figlio = figlio;
	}

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


	public Genitore() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Genitore(long id, String nome, String cognome, String riferimento, Utente utente, Studente figlio) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.riferimento = riferimento;
		this.utente = utente;
		this.figlio = figlio;
	}

	@Override
	public String toString() {
		return "Genitore [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", riferimento=" + riferimento
				+ ", utente=" + utente + ", figlio=" + figlio + "]";
	}

	
	
}

