package com.nelief.WebClass.service;

import com.nelief.WebClass.entity.Utente;

public interface HomeService {
		Utente getUtenteByUsername(String username);
}
