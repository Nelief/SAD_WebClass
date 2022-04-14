package com.nelief.WebClass.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelief.WebClass.entity.Classe;

@Repository
public interface ClassiRepository extends JpaRepository<Classe, Long> {

}
