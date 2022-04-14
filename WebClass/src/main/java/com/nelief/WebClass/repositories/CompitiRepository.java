package com.nelief.WebClass.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelief.WebClass.entity.Classe;
import com.nelief.WebClass.entity.Compito;

@Repository
public interface CompitiRepository extends JpaRepository<Compito, Long> {

	List<Compito> findAllByClasse(Classe classeAttiva);

}
