package com.nelief.WebClass.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nelief.WebClass.entity.Cattedra;
import com.nelief.WebClass.entity.CattedraInfoDTO;
import com.nelief.WebClass.entity.Classe;
import com.nelief.WebClass.entity.Docente;
import com.nelief.WebClass.entity.DocenteDTO;
import com.nelief.WebClass.entity.Genitore;
import com.nelief.WebClass.entity.GenitoreDTO;
import com.nelief.WebClass.entity.Studente;
import com.nelief.WebClass.entity.Utente;
import com.nelief.WebClass.repositories.CattedreRepository;
import com.nelief.WebClass.repositories.ClassiRepository;
import com.nelief.WebClass.repositories.DocentiRepository;
import com.nelief.WebClass.repositories.GenitoriRepository;
import com.nelief.WebClass.repositories.StudentiRepository;
import com.nelief.WebClass.repositories.UtentiRepository;

@Service
public class AdminServiceImpl implements AdminService{

	private DocentiRepository docentiRepository;
	private UtentiRepository utentiRepository;
	private ClassiRepository classiRepository;
	private CattedreRepository cattedreRepository;
	private StudentiRepository studentiRepository;
	private GenitoriRepository genitoriRepository;


	public AdminServiceImpl(DocentiRepository docentiRepository, UtentiRepository utentiRepository,
			ClassiRepository classiRepository, CattedreRepository cattedreRepository,
			StudentiRepository studentiRepository, GenitoriRepository genitoriRepository) {
		super();
		this.docentiRepository = docentiRepository;
		this.utentiRepository = utentiRepository;
		this.classiRepository = classiRepository;
		this.cattedreRepository = cattedreRepository;
		this.studentiRepository = studentiRepository;
		this.genitoriRepository = genitoriRepository;
	}


	/*
	 * Chiama il Repository per ottenere una lista di docenti 
	 */
	@Override
	public List<Docente> getAllDocenti() {
		return docentiRepository.findAll();
	}

	
	/*
	 * compila 2 oggetti Utente e Docente estraendo i dati necessari dal DTO ricevuto e chiama il repository per l' inserimento in DB  
	 */
	@Override
	public void registraDocente(DocenteDTO dto) {
		
		Utente utente = new Utente();
		utente.setUsername(dto.getUsername());
		utente.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
		utente.setEmail(dto.getEmail());
		utente.setRuolo("DOCENTE");
		
		utentiRepository.save(utente);
		
		Docente docente = new Docente();
		docente.setNome(dto.getNome());
		docente.setCognome(dto.getCognome());
		docente.setMateria(dto.getMateria());
		docente.setRiferimento(dto.getRiferimento());
		docente.setUtente(utente);
		
		docentiRepository.save(docente);
	}

	/*
	 * Chiama il Repository per l' estrazione di un Docente dal DB in base all' ID e, dopo averne verificato l' esistenza, lo ritorna al controller
	 */
	@Override
	public Docente getDocenteByid(long id) {
		
		Optional<Docente> OptDocente = docentiRepository.findById(id);
		Docente docente = null;
		if(OptDocente.isPresent()) {
			docente = OptDocente.get();
		}
		else {
			throw new RuntimeException("Docente not found with id ::" + id);
		}
		return docente;
	}

	/*
	 * Richiede l' inserimento di un docente, è impiegato come metodo di update delle sole informazioni docente, senza modificarne l' account utente
	 */
	@Override
	public void aggiornaDocente(Docente docente) {
		docentiRepository.save(docente);
	}

	/*
	 * Chiama il Repository per ottenere una lista di Classi 
	 */
	@Override
	public List<Classe> getAllClassi() {
		return classiRepository.findAll();
	}

	/*
	 * Richiede l' inserimento di una nuova classe dopo averne richiesto la correzione 
	 */
	@Override
	public void inserisciClasse(Classe classe) {
		classe.correct();
		classiRepository.save(classe);
	}

	/*
	 * Chiama il Repository per l' estrazione di una classe  dal DB in base all' ID e, dopo averne verificato l' esistenza, la ritorna al controller
	 */
	@Override
	public Classe getClasseById(long id) {
		Optional<Classe> OptClasse = classiRepository.findById(id);
		Classe classe = null;
		if(OptClasse.isPresent()) {
			classe = OptClasse.get();
		}
		else {
			throw new RuntimeException("Classe not found with id ::" + id);
		}
		return classe;
	}


	/*
	 * Metodo di estrazione delle cattedre: Viene prima di tutto estratta una lista di cattedre dal DB (idcattedra,iddocente,idclasse) e creata una lista di DTO vuota
	 * , tale lista è poi riempità iterativamente da DTO nei quali vengono inserite le informazioni estratte precedentemente in cattedra; il repository JPA si occupa 
	 * del processo di estrazione di docenti e classi contenuti in cattedre
	 */
	@Override
	public List<CattedraInfoDTO> getCattedreByClasse(Classe classe) {
		
		List<Cattedra> cattedre = cattedreRepository.findAllByClasse(classe); //estrae tutte le cattedre
		
		List<CattedraInfoDTO> DTOs = new ArrayList<CattedraInfoDTO>();

		for(Cattedra cattedra : cattedre) {
			
			CattedraInfoDTO dto = new CattedraInfoDTO();
			dto.setId(cattedra.getId());
			dto.setNomeDocente(cattedra.getDocente().getNome());
			dto.setCognomeDocente(cattedra.getDocente().getCognome());
			dto.setMateria(cattedra.getDocente().getMateria());
			
			DTOs.add(dto);
		}
		
		return DTOs;
	}

	/*
	 * estrae docente e classe in base agli ID ricevuti e crea una nuova cattedra della quale richiede l' inserimento in DB
	 */
	@Override
	public void inserisciCattedra(long idd, long idc) {
		Docente doc = docentiRepository.getById(idd);
		Classe classe = classiRepository.getById(idc);
		
		Cattedra cattedra = new Cattedra();
		cattedra.setDocente(doc);
		cattedra.setClasse(classe);
		
		cattedreRepository.save(cattedra);
	}

	/*
	 * Chiama il Repository per ottenere una lista di studenti associati alla classe ricevuta 
	 */
	@Override
	public List<Studente> getStudentiByClass(Classe classe) {
		return studentiRepository.findAllByClasse(classe);
	}

	/*
	 * Richiede l' inserimento di un nuovo studente;
	 */
	@Override
	public void inserisciStudente(Studente studente) {
		studentiRepository.save(studente);
	}

	/*
	 * Chiama il Repository per l' estrazione di uno studente  dal DB in base all' ID e, dopo averne verificato l' esistenza, lo ritorna al controller
	 */
	@Override
	public Studente getStudenteByid(long id_studente) {
		Optional<Studente> OptStudente = studentiRepository.findById(id_studente);
		Studente studente = null;
		if(OptStudente.isPresent()) {
			studente = OptStudente.get();
		}
		else {
			throw new RuntimeException("Classe not found with id ::" + id_studente);
		}
		return studente;
	}


	@Override
	public boolean VerifyGenitore(long id_studente) {
			Optional<Studente> optstudente = studentiRepository.findById(id_studente);
			Studente studente = optstudente.get();
			return genitoriRepository.existsByfiglio(studente);	
	}


	@Override
	public Genitore getGenitoreByIdStudente(long id_studente) {
		Optional<Studente> optstudente = studentiRepository.findById(id_studente);
		Studente studente = optstudente.get();
		return genitoriRepository.findByfiglio(studente);
	}


	@Override
	public void registraGenitore(GenitoreDTO dto) {
			Utente utente = new Utente();
			utente.setUsername(dto.getUsername());
			utente.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
			utente.setEmail(dto.getEmail());
			utente.setRuolo("GENITORE");
			
			
			
			Genitore genitore = new Genitore();
			genitore.setNome(dto.getNome());
			genitore.setCognome(dto.getCognome());
			genitore.setRiferimento(dto.getRiferimento());
			genitore.setUtente(utente);
			
			Studente studente = studentiRepository.getById(dto.getId_figlio());
			genitore.setFiglio(studente);
			
			utentiRepository.save(utente);
			genitoriRepository.save(genitore);
			
	}

}
