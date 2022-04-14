package com.nelief.WebClass.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nelief.WebClass.entity.Cattedra;
import com.nelief.WebClass.entity.Classe;
import com.nelief.WebClass.entity.Compito;
import com.nelief.WebClass.entity.Docente;
import com.nelief.WebClass.entity.Studente;
import com.nelief.WebClass.entity.Utente;
import com.nelief.WebClass.repositories.CattedreRepository;
import com.nelief.WebClass.repositories.ClassiRepository;
import com.nelief.WebClass.repositories.CompitiRepository;
import com.nelief.WebClass.repositories.DocentiRepository;
import com.nelief.WebClass.repositories.StudentiRepository;
import com.nelief.WebClass.repositories.UtentiRepository;

@Service
public class DocentiServiceImpl implements DocentiService {

	private UtentiRepository utentiRepository;
	private DocentiRepository docentiRepository;
	private CattedreRepository cattedreRepository;
	private ClassiRepository classiRepository;
	private StudentiRepository studentiRepository;
	private CompitiRepository compitiRepository;


	public DocentiServiceImpl(UtentiRepository utentiRepository, DocentiRepository docentiRepository,
			CattedreRepository cattedreRepository, ClassiRepository classiRepository,
			StudentiRepository studentiRepository, CompitiRepository compitiRepository) {
		super();
		this.utentiRepository = utentiRepository;
		this.docentiRepository = docentiRepository;
		this.cattedreRepository = cattedreRepository;
		this.classiRepository = classiRepository;
		this.studentiRepository = studentiRepository;
		this.compitiRepository = compitiRepository;
	}

	@Override
	public Utente getUtenteByUsername(String principalName) {
		return utentiRepository.findByUsername(principalName);
	}

	@Override
	public Docente getDocenteByUtente(Utente authUser) {
		return docentiRepository.findByUtente(authUser);
	}

	@Override
	public List<Classe> getClassiDocente(Docente docenteAttivo) {
		List<Cattedra> cattedre = cattedreRepository.findAllByDocente(docenteAttivo);

		List<Classe> classi = new ArrayList<>();

		for (Cattedra c : cattedre) {
			classi.add(c.getClasse());
		}

		return classi;
	}

	@Override
	public Classe getClasseById(long id) {
		Optional<Classe> optclasse = classiRepository.findById(id);
		return optclasse.get();
	}

	@Override
	public List<Studente> getStudentiByClass(Classe classeAttiva) {
		return studentiRepository.findAllByClasse(classeAttiva);
	}

	@Override
	public List<Compito> getCompitiDocenteByClasse(Classe classeAttiva, Docente docenteAttivo) {
		List<Compito> compitiClasse = compitiRepository.findAllByClasse(classeAttiva);
		
		List<Compito> compitiDocente = new ArrayList<Compito>();
		
		for(Compito c : compitiClasse) {
			if(c.getDocente().getId() == docenteAttivo.getId())
			{
				compitiDocente.add(c);
			}
		}
		return compitiDocente;
	}

	@Override
	public void saveCompito(Compito compito, Classe classeAttiva, Docente docenteAttivo) {
		
		Classe validClasse = classiRepository.getById(classeAttiva.getId());
		
		Docente validDoc = docentiRepository.getById(docenteAttivo.getId());
		
		compito.setClasse(validClasse);
		compito.setDocente(validDoc);
		compito.setMateria(validDoc.getMateria());
		compitiRepository.save(compito);
	}

	

}
