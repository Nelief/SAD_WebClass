package com.nelief.WebClass.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nelief.WebClass.entity.Classe;
import com.nelief.WebClass.entity.Compito;
import com.nelief.WebClass.entity.Docente;
import com.nelief.WebClass.entity.Studente;
import com.nelief.WebClass.entity.Utente;
import com.nelief.WebClass.service.DocentiService;

@Controller
public class DocenteController {

	private DocentiService docentiService;
	
	private ModelAndView mav;
	
	private Docente docenteAttivo; //Docente in sessione

	private Classe classeAttiva;
	
	public DocenteController(DocentiService docentiService) {
		super();
		this.docentiService = docentiService;
		mav = new ModelAndView();
	}
	

	@GetMapping("/listaClassiDocente")
	public ModelAndView getClassiDocente(Principal principal) {
		docenteAttivo = new Docente();
		setDocenteAttivo(principal.getName());
		
		List<Classe> classiDocente = docentiService.getClassiDocente(docenteAttivo);
		
		mav.addObject("docente",docenteAttivo);
		mav.addObject("classi", classiDocente );
		mav.setViewName("docente/listaClassiDocente");
		return mav;
	}
	
	public void setDocenteAttivo(String principalName) {
		Utente authUser = docentiService.getUtenteByUsername(principalName); //estrae l' utente attivo 
		docenteAttivo = docentiService.getDocenteByUtente(authUser);//associa al docente attivo l' oggetto estratto in base all' utente attivo
	}
	
	@GetMapping("/listaStudentiDocente/{id}")
	public ModelAndView getStudentiClasse(@PathVariable(value="id")long id) {
		classeAttiva = docentiService.getClasseById(id); //setto la classe attiva in base all' id richiesto
		
		List<Studente> studenti = docentiService.getStudentiByClass(classeAttiva);
		
		mav.addObject("classe",classeAttiva);
		mav.addObject("studenti",studenti);
		mav.setViewName("docente/listaStudentiDocente");
		return mav;
	}
	
	@GetMapping("/listaCompitiClasse")
	public ModelAndView getCompitiClasse() {
		if(classeAttiva == null) {
			mav.setViewName("redirect:/listaClassiDocente"); //sanity check, nel caso il si acceda direttamente alla lista compiti senza passare per l' assegnazione della classe 
		}
		else {
			List<Compito> compiti = docentiService.getCompitiDocenteByClasse(classeAttiva,docenteAttivo);
			
			mav.addObject("compiti",compiti);
			mav.addObject("classe",classeAttiva);
			mav.setViewName("docente/listaCompitiDocente");
		}
		return mav;
	}
	
	@GetMapping("/formAddCompito")
	public ModelAndView getFormCompito() {
		Compito compito = new Compito();
		mav.addObject("compito",compito);
		mav.setViewName("docente/inserisciCompito");
		return mav;
	}
	
	@PostMapping("/saveCompito")
	public ModelAndView saveCompito(@ModelAttribute("compito") Compito compito) {
		docentiService.saveCompito(compito,classeAttiva,docenteAttivo);
		
		return getCompitiClasse();
	}
}
