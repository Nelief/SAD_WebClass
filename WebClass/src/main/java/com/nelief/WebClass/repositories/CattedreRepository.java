package com.nelief.WebClass.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelief.WebClass.entity.Cattedra;
import com.nelief.WebClass.entity.Classe;
import com.nelief.WebClass.entity.Docente;

public interface CattedreRepository extends JpaRepository<Cattedra, Long> {
	List<Cattedra> findAllByClasse(Classe classe);

	List<Cattedra> findAllByDocente(Docente docenteAttivo);
}
