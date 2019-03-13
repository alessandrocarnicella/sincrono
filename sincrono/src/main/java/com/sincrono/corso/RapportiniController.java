package com.sincrono.corso;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RapportiniController {
	
	@RequestMapping(value = "/Rapportini")
	public String getRapportini(Model m, HttpServletRequest request) {
		
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
		
		return "Rapportini";
	}
	
	@RequestMapping(value = "/GestioneCommesseDipendenti")
	public String getGestioneCommesseDipendenti(Model m, HttpServletRequest request) {
		
		if(!(boolean) request.getSession().getAttribute("isLogged")) {
			return "Login";
		}
		
		return "GestioneCommesseDipendenti";
	}

}
