package com.sincrono.corso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sincrono.corso.model.DipendenteService;

@Controller
public class DipendenteController {
		
	@Autowired
	DipendenteService dip;
	
	@RequestMapping(value = "/Utenti")
	public String getUtenti(Model m) {
		m.addAttribute("list_dip", dip.findAll());
		return "Utenti";
	}
	
	@RequestMapping(value = "/GestioneUtenti")
	public String getGestioneUtent(Model m) {
		m.addAttribute("list_dip", dip.findAll());
		return "GestioneUtent";
	}
	
}
