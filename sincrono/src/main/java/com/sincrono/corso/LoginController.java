package com.sincrono.corso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sincrono.corso.model.AziendaService;
import com.sincrono.corso.model.DipendenteService;

@Controller
public class LoginController {

	@Autowired
	AziendaService as;
	
	@Autowired
	DipendenteService dip;
	

	@RequestMapping(value = "/")
	public String getHome(Model m) {
	
		return "Login";
	}
	
	@RequestMapping(value = "/Dashboard")
	public String getDashboard(Model m) {
		m.addAttribute("list", as.findAll());
		return "Dashboard";
	}
	
	@RequestMapping(value = "/Dipendenti")
	public String getDipendenti(Model m) {
		m.addAttribute("list_dip", dip.findAll());
		return "Dipendenti";
	}
	
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
