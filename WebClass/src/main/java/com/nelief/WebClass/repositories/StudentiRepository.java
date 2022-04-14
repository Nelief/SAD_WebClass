package com.nelief.WebClass.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelief.WebClass.entity.Classe;
import com.nelief.WebClass.entity.Studente;

public interface StudentiRepository extends JpaRepository<Studente, Long> {
	List<Studente> findAllByClasse(Classe classe);

	
}
