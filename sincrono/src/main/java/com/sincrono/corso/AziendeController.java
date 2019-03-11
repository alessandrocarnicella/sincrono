package com.sincrono.corso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sincrono.corso.model.AziendaService;

@Controller
public class AziendeController {

	
	@Autowired
	AziendaService as;
	
	@RequestMapping(value = "/Aziende")
	public String getAziende(Model m) {
		
		m.addAttribute("list_az", as.findAll());
		return "Aziende";
	}
	
	@RequestMapping(value = "/GestioneAziende")
	public String getGestioneAziende(Model m) {
		
		m.addAttribute("list_az", as.findAll());
		return "GestioneAziende";
	}
	
	
	
}
