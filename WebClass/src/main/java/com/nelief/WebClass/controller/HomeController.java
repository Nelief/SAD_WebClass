package com.nelief.WebClass.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nelief.WebClass.entity.Utente;
import com.nelief.WebClass.service.HomeService;

@Controller
public class HomeController {
	
	private HomeService homeService;
	 
	public HomeController(HomeService homeService) {
	super();
	this.homeService = homeService;
	}

	/*
	 * Controller iniziale dell' applicazione, che porta alla pagina di index (Cattura anche URL risultanti da un logout completato con successo)
	 */
	@GetMapping({"/","/logout_success"})
	public ModelAndView getIndex(){
		ModelAndView mav = new ModelAndView("index");
		return mav; 
	}
	
	/*
	 *  Controller di "smistamento", che va a caricare Principale (l' utente autenticato, contenuto nel security context dopo aver effettuato il login) 
	 *  , Estrae dal DB l' utente associato all' username e seleziona, in base al suo ruolo, la corretta homepage.
	 */
	@GetMapping({"/home"})
	public ModelAndView getHome(Principal principal) {
		ModelAndView mav = new ModelAndView();
		Utente authUser = homeService.getUtenteByUsername(principal.getName());
		
		switch(authUser.getRuolo()) {
		case "DOCENTE":  mav.setViewName("homeDocente"); break;
		case "GENITORE": mav.setViewName("homeGenitore"); break;
		case "ADMIN": mav.setViewName("homeAdmin"); break;
		}
		
		mav.addObject("username",authUser.getUsername());
		return mav;
	}
	
	/*
	 * Controller chiamato dal filtro di autenticazione (nativo di Spring Security) che reindirizza alla corretta pagina di login custom 
	 */
	@GetMapping({"/login"})
	public ModelAndView getLogin() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
}
