package com.nelief.WebClass.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "cattedre")
public class Cattedra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@ManyToOne
	@JoinColumn(name="docente_id",referencedColumnName = "id")
	private Docente docente;
	
	@ManyToOne
	@JoinColumn(name="classe_id",referencedColumnName = "id")
	private Classe classe;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Cattedra(long id, Docente docente, Classe classe) {
		super();
		this.id = id;
		this.docente = docente;
		this.classe = classe;
	}

	public Cattedra() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cattedra [id=" + id + ", docente=" + docente + ", classe=" + classe + "]";
	}
	
}
