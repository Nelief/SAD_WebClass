package com.nelief.WebClass.service;

import java.util.List;

import com.nelief.WebClass.entity.CattedraInfoDTO;
import com.nelief.WebClass.entity.Classe;
import com.nelief.WebClass.entity.Docente;
import com.nelief.WebClass.entity.DocenteDTO;
import com.nelief.WebClass.entity.Genitore;
import com.nelief.WebClass.entity.GenitoreDTO;
import com.nelief.WebClass.entity.Studente;

public interface AdminService {
	
	List<Docente> getAllDocenti();
	void registraDocente(DocenteDTO dto);
	Docente getDocenteByid(long id);
	void aggiornaDocente(Docente docente);
	
	List<Classe> getAllClassi();
	void inserisciClasse(Classe classe);
	Classe getClasseById(long id);
	
	List<CattedraInfoDTO> getCattedreByClasse(Classe classe);
	void inserisciCattedra(long idd, long idc);
	
	List<Studente> getStudentiByClass(Classe classe);
	void inserisciStudente(Studente studente);
	Studente getStudenteByid(long id_studente);
	
	boolean VerifyGenitore(long id_studente);
	Genitore getGenitoreByIdStudente(long id_studente);
	void registraGenitore(GenitoreDTO dto);
}
