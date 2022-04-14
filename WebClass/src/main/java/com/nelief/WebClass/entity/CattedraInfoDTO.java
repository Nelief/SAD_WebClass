package com.nelief.WebClass.entity;

import org.springframework.stereotype.Component;


@Component
public class CattedraInfoDTO {
	private long id;
	private String nomeDocente;
	private String cognomeDocente;
	private String materia;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeDocente() {
		return nomeDocente;
	}
	public void setNomeDocente(String nomeDocente) {
		this.nomeDocente = nomeDocente;
	}
	public String getCognomeDocente() {
		return cognomeDocente;
	}
	public void setCognomeDocente(String cognomeDocente) {
		this.cognomeDocente = cognomeDocente;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public CattedraInfoDTO(long id, String nomeDocente, String cognomeDocente, String materia) {
		super();
		this.id = id;
		this.nomeDocente = nomeDocente;
		this.cognomeDocente = cognomeDocente;
		this.materia = materia;
	}
	public CattedraInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CattedraInfoDTO [id=" + id + ", nomeDocente=" + nomeDocente + ", cognomeDocente=" + cognomeDocente
				+ ", materia=" + materia + "]";
	}
	
	
}
