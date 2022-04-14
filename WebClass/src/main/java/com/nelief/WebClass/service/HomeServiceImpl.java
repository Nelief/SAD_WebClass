package com.nelief.WebClass.service;

import org.springframework.stereotype.Service;

import com.nelief.WebClass.entity.Utente;
import com.nelief.WebClass.repositories.UtentiRepository;

@Service
public class HomeServiceImpl implements HomeService {

    private UtentiRepository utentiRepository;  
    
	public HomeServiceImpl(UtentiRepository utentiRepository) {
		super();
		this.utentiRepository = utentiRepository;
	}

	@Override
	public Utente getUtenteByUsername(String username) {
		return utentiRepository.findByUsername(username);
	}

}
