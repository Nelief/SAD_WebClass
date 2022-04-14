package com.nelief.WebClass.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelief.WebClass.entity.Docente;
import com.nelief.WebClass.entity.Utente;

public interface DocentiRepository extends JpaRepository<Docente, Long> {
	Docente findByUtente(Utente utente);
}
