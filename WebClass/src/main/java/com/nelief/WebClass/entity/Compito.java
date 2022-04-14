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
@Table(name = "compiti")
public class Compito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String materia;
	private String descrizione;
	private String data;
	private String dataconsegna;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "docenteId", referencedColumnName = "id")
	private Docente docente;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classId", referencedColumnName = "id")
	private Classe classe;
	
	public Compito(long id, String materia, String descrizione, String data, String dataconsegna) {
		super();
		this.id = id;
		this.materia = materia;
		this.descrizione = descrizione;
		this.data = data;
		this.dataconsegna = dataconsegna;
	}
	public Compito() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDataconsegna() {
		return dataconsegna;
	}
	public void setDataconsegna(String dataconsegna) {
		this.dataconsegna = dataconsegna;
	}
	@Override
	public String toString() {
		return "Compito [id=" + id + ", materia=" + materia + ", descrizione=" + descrizione + ", data=" + data
				+ ", dataconsegna=" + dataconsegna + "]";
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
	
}
