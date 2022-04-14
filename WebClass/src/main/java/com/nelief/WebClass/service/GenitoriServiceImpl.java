package com.nelief.WebClass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nelief.WebClass.entity.Classe;
import com.nelief.WebClass.entity.Compito;
import com.nelief.WebClass.entity.Genitore;
import com.nelief.WebClass.entity.Utente;
import com.nelief.WebClass.repositories.CompitiRepository;
import com.nelief.WebClass.repositories.GenitoriRepository;
import com.nelief.WebClass.repositories.UtentiRepository;

@Service
public class GenitoriServiceImpl implements GenitoriService {

	private UtentiRepository utentiRepository;
	private GenitoriRepository genitoriRepository;
	private CompitiRepository compitiRepository;
	
	public GenitoriServiceImpl(UtentiRepository utentiRepository, GenitoriRepository genitoriRepository,
			CompitiRepository compitiRepository) {
		super();
		this.utentiRepository = utentiRepository;
		this.genitoriRepository = genitoriRepository;
		this.compitiRepository = compitiRepository;
	}

	@Override
	public Utente getUtenteByUsername(String principalName) {
		return utentiRepository.findByUsername(principalName);
	}
	
	@Override
	public Genitore getGenitoreByUtente(Utente authUser) {
		return genitoriRepository.findByUtente(authUser);
	}

	@Override
	public List<Compito> getCompitiByClasse(Classe classe) {
		return compitiRepository.findAllByClasse(classe);
	}


}
