package com.nelief.WebClass.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nelief.WebClass.entity.Compito;
import com.nelief.WebClass.entity.Genitore;
import com.nelief.WebClass.entity.Utente;
import com.nelief.WebClass.service.GenitoriService;

@Controller
public class GenitoreController {
	
	private GenitoriService genitoriService;

	private ModelAndView mav;
	
	private Genitore genitoreAttivo;
	
	public GenitoreController(GenitoriService genitoriService) {
		super();
		this.genitoriService = genitoriService;
		mav = new ModelAndView();
	}
	
	
	@GetMapping("/anagraficaFiglio")
	public ModelAndView getInfoFiglio(Principal principal) {
		genitoreAttivo = new Genitore();
		setGenitoreAttivo(principal.getName());
		
		mav.addObject("genitore",genitoreAttivo);
		mav.setViewName("genitore/anagraficaFiglio");

		return mav;
	}
	
	public void setGenitoreAttivo(String principalName) {
		Utente authUser = genitoriService.getUtenteByUsername(principalName); 
		genitoreAttivo = genitoriService.getGenitoreByUtente(authUser);
	}
	
	@GetMapping("/getListaCompiti")
	public ModelAndView getListaCompiti(){
		List<Compito> compiti = genitoriService.getCompitiByClasse(genitoreAttivo.getFiglio().getClasse());
	
		mav.addObject("compiti",compiti);
		mav.setViewName("genitore/listaCompitiStudente");
		return mav;
	}
	
}
