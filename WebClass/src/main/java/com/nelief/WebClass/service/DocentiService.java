package com.nelief.WebClass.service;

import java.util.List;

import com.nelief.WebClass.entity.Classe;
import com.nelief.WebClass.entity.Compito;
import com.nelief.WebClass.entity.Docente;
import com.nelief.WebClass.entity.Studente;
import com.nelief.WebClass.entity.Utente;

public interface DocentiService {

	Utente getUtenteByUsername(String principalName);

	Docente getDocenteByUtente(Utente authUser);

	List<Classe> getClassiDocente(Docente docenteAttivo);

	Classe getClasseById(long id);

	List<Studente> getStudentiByClass(Classe classeAttiva);

	List<Compito> getCompitiDocenteByClasse(Classe classeAttiva, Docente docenteAttivo);

	void saveCompito(Compito compito, Classe classeAttiva, Docente docenteAttivo);

}
