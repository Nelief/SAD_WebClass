package com.nelief.WebClass.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nelief.WebClass.entity.CattedraInfoDTO;
import com.nelief.WebClass.entity.Classe;
import com.nelief.WebClass.entity.Docente;
import com.nelief.WebClass.entity.DocenteDTO;
import com.nelief.WebClass.entity.Genitore;
import com.nelief.WebClass.entity.GenitoreDTO;
import com.nelief.WebClass.entity.Studente;
import com.nelief.WebClass.service.AdminService;

@Controller
public class AdminController {

	private AdminService adminService;
	
	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	/*
	 * Richiede la lista completa di docenti al DB e li ritorna nella pagina listaDocenti
	 */
	@GetMapping("/listaDocenti")
	public ModelAndView getDocenti() {
		
		
		List<Docente> docenti = adminService.getAllDocenti();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("docenti", docenti);
		mav.setViewName("admin/listaDocenti");
		
		return mav;
	}
	
	/*
	 * Crea un nuovo oggetto DocenteDTO e ritorna la pagina InserisciDocente per la compilazione
	 */
	@GetMapping("/formAddDocente")
	public ModelAndView getFormDocente() {
	
		DocenteDTO dto = new DocenteDTO();
		
		ModelAndView mav = new ModelAndView("admin/inserisciDocente");
		mav.addObject("docentedto", dto);
		return mav;
	}
	
	/*
	 * Richiede la registrazione del nuovo docente ed utente associato, richiama il metodo getDocenti per tornare alla listaDocenti
	 */
	@PostMapping("/saveDocente")
	public ModelAndView saveDocente(@ModelAttribute("docentedto") DocenteDTO dto) {
		adminService.registraDocente(dto);
		return getDocenti();
	}
	
	/*
	 * Richiede le informazioni del docente in base all' ID ricevuto e ritorna la pagina UpdateDocente per la modifica
	 */
	@GetMapping("/formUpdateDocente/{id}")
	public ModelAndView getFormUpdateDocente(@PathVariable( value ="id") long id) {	
		
		
		Docente docente = adminService.getDocenteByid(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/updateDocente");
		mav.addObject("docente",docente);
		
		return mav;
	}
	
	/*
	 * Richiede l' aggiornamento del docente e chiama getDocenti per ritornare la listaDocenti
	 */
	@PostMapping("/updateDocente")
	public ModelAndView updateDocente(@ModelAttribute("docente") Docente docente) {
		adminService.aggiornaDocente(docente);
		return getDocenti();
	}
	
	/*
	 * Richiede la lista delle classi dal DB e ritorna ListaClassi
	 */
	@GetMapping("/listaClassi")
	public ModelAndView getClassi() {
		
		List<Classe> classi = adminService.getAllClassi();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("classi", classi);
		mav.setViewName("admin/listaClassi");
		
		return mav;
	}
	
	/*
	 * Crea un nuovo oggetto Classe e ritrna la vista inserisciClasse per la compilazione
	 */
	@GetMapping("/formAddClasse")
	public ModelAndView getFormClasse() {
		
		Classe classe = new Classe();
		
		ModelAndView mav = new ModelAndView("admin/inserisciClasse");
		mav.addObject("classe", classe);
		return mav;
	}
	
	/*
	 * richiede l' inserimento della nuova classe e chiama getClassi per ritornare ListaClassi
	 */
	@PostMapping("/saveClasse")
	public ModelAndView saveClasse(@ModelAttribute("classe") Classe classe) {
		adminService.inserisciClasse(classe);
		return getClassi();
	}
	
	/*
	 * Richiede le informazioni della classe in base all' ID ricevuto e ritorna la vista UpdateClasse per la compilazione
	 */
	@GetMapping("/formUpdateClasse/{id}")
	public ModelAndView GetFormUpdateClasse(@PathVariable( value ="id") long id) {	
		
		Classe classe = adminService.getClasseById(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/updateClasse");
		mav.addObject("classe",classe);
		
		return mav;
	}
	
	/*
	 * Richiede le informazioni della classe in base all' ID ricevuto e successivamente le informazioni composite 
	 * CattedraInfoDto (Cattedra + docente assegnato) in base alla classe estratta, ritorna poi la vista ListaCattedre
	 */
	@GetMapping("/listaCattedre/{id}")
	public ModelAndView getCattedreClasse(@PathVariable( value ="id") long id) {
		Classe classe = adminService.getClasseById(id);
		
		List<CattedraInfoDTO> DTO = adminService.getCattedreByClasse(classe);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("classe", classe);
		mav.addObject("DTOs", DTO);
		mav.setViewName("admin/listaCattedre");
		return mav;
	}
	
	/*
	 * Richiede l' estrazione delle informazioni di tutti i docenti dall DB, e della classe in base all' ID ricevuto, 
	 * ritornando la vista AssociaDocente per l' associazione di un docente alla classe (Creazione di una nuova cattedra)
	 */
	@GetMapping("/formAddCattedra/{id}")
	public ModelAndView getFormCattedra(@PathVariable(value="id") long id) {
		
		List<Docente> docList = adminService.getAllDocenti();
		Classe classe = adminService.getClasseById(id);
		
		ModelAndView mav = new ModelAndView("admin/associaDocente");
		mav.addObject("classe",classe);
		mav.addObject("docenti", docList);
		
		return mav;
	}
	
	/*
	 * Richiede l' inserimento di una nuova cattedra nel sistema in base agli ID di Classe e Docente ricevuti 
	 */
	@PostMapping("/saveCattedra/{id_doc}/{id_classe}")
	public ModelAndView saveCattedra(
				@PathVariable( value ="id_doc") long idd,
				@PathVariable( value ="id_classe") long idc) {
		
		adminService.inserisciCattedra(idd,idc);
		
		return getCattedreClasse(idc);
	}
	

	/*
	 * Richiede le informazioni della classe in base all' ID ricevuto, richiede poi la lista degli studenti in base alla classe,
	 * ritorna la vista ListaStudenti
	 */
	@GetMapping("/listaStudenti/{id}")
	public ModelAndView getStudentiClasse(@PathVariable(value="id")long id) {
		
		
		Classe classe = adminService.getClasseById(id);
		
		List<Studente> studenti = adminService.getStudentiByClass(classe);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("studenti",studenti);
		mav.addObject("classe",classe);
		mav.setViewName("admin/listaStudenti");
		return mav;
	}
	
	
	/*
	 * Richiede le informazioni della classe in base all' ID, crea un nuovo oggetto studente vuoto e ritorna la vista inserisciStudente per la compilazione
	 */
	@GetMapping("/formAddStudente/{id}")
	public ModelAndView getFormStudente(@PathVariable(value="id")long id) {
		
		
		Classe classe = adminService.getClasseById(id);
		Studente studente = new Studente();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("studente",studente);
		mav.addObject("classe",classe);
		mav.setViewName("admin/inserisciStudente");
		
		return mav; 
	}
	
	/*
	 * Richiede la classe in base all' ID, e dopo aver settato la classe correttamente nello studente, richiede l' inserimento del nuovo studente in DB
	 * , chiama poi getStudentiClasse per ritornare alla vista ListaStudenti della data classe 
	 */
	@PostMapping("/saveStudente/{id}")
	public ModelAndView saveStudente(@PathVariable(value="id") long id , @ModelAttribute("studente") Studente studente) {
		
		Classe classe = adminService.getClasseById(id);
		
		studente.setClasse(classe);
		
		adminService.inserisciStudente(studente);
		
		return getStudentiClasse(id);
	}
	
	
	/*
	 * Richiede i dati di studente e classe in base agli ID ricevuti, e ritorna la vista UpdateStudente per l' aggiornamento dei dati. 
	 */
	@GetMapping("/formUpdateStudente/{idc}/{ids}")
	public ModelAndView getFormUpdateStudente(@PathVariable(value="idc") long id_classe ,@PathVariable(value="ids") long id_studente) {
		
		
		Studente studente = adminService.getStudenteByid(id_studente);
		Classe classe = adminService.getClasseById(id_classe); 
			
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/updateStudente");
		mav.addObject("classe",classe);
		mav.addObject("studente",studente);
		
		return mav;
	}
	
	
	@GetMapping("/infoGenitore/{idc}/{ids}")
	public ModelAndView getInfoGenitore(@PathVariable(value="idc") long id_classe ,@PathVariable(value="ids") long id_studente) {
		ModelAndView mav = new ModelAndView();
		
		Classe classe = adminService.getClasseById(id_classe);
		mav.addObject("classe",classe);
		
		boolean check = adminService.VerifyGenitore(id_studente);
		
		if(check) {
			Genitore genitore = adminService.getGenitoreByIdStudente(id_studente);
			mav.addObject("genitore",genitore);
			mav.setViewName("admin/infoGenitore");	
		}
		else {
			GenitoreDTO DTO = new GenitoreDTO();
			mav.addObject("genitoredto",DTO);
			
			mav.addObject("idfiglio",id_studente);
			mav.setViewName("admin/inserisciGenitore");
		}
		return mav;
	}
	
	
	@PostMapping("/saveGenitore/{idc}/{ids}")
	public ModelAndView saveGenitore(
			@PathVariable(value="idc") long id_classe ,
			@PathVariable(value="ids") long id_studente , 
			@ModelAttribute("genitoredto") GenitoreDTO dto) {
		dto.setId_figlio(id_studente);
		adminService.registraGenitore(dto);
		return getInfoGenitore(id_classe,dto.getId_figlio());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
