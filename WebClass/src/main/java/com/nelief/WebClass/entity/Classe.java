package com.nelief.WebClass.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="classi")
public class Classe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int grado;
	private String sezione;
	private int anno;
	
	public void correct() {
		this.sezione = this.sezione.toUpperCase();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGrado() {
		return grado;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}

	public String getSezione() {
		return sezione;
	}

	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public Classe(long id, int grado, String sezione, int anno) {
		super();
		this.id = id;
		this.grado = grado;
		this.sezione = sezione;
		this.anno = anno;
	}

	public Classe() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Classe [id=" + id + ", grado=" + grado + ", sezione=" + sezione + ", anno=" + anno + "]";
	}

}
