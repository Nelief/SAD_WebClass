package com.nelief.WebClass.service;

import java.util.List;

import com.nelief.WebClass.entity.Classe;
import com.nelief.WebClass.entity.Compito;
import com.nelief.WebClass.entity.Genitore;
import com.nelief.WebClass.entity.Utente;

public interface GenitoriService {

	Utente getUtenteByUsername(String principalName);

	Genitore getGenitoreByUtente(Utente authUser);

	List<Compito> getCompitiByClasse(Classe classe);


}
