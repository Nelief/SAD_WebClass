package com.nelief.WebClass.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelief.WebClass.entity.Genitore;
import com.nelief.WebClass.entity.Studente;
import com.nelief.WebClass.entity.Utente;

public interface GenitoriRepository extends JpaRepository<Genitore, Long> {
	boolean existsByfiglio(Studente studente);
	Genitore findByfiglio(Studente studente);
	Genitore findByUtente(Utente authUser); 
}
