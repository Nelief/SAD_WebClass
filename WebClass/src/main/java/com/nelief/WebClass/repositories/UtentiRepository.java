package com.nelief.WebClass.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelief.WebClass.entity.Utente;

@Repository
public interface UtentiRepository extends JpaRepository<Utente, Long> {
	Utente findByUsername(String username);
}
